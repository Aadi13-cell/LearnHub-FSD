const urlParams = new URLSearchParams(window.location.search);
const pdfUrl = urlParams.get('pdf');

let pdfDoc = null,
    pageNum = 1,
    pageIsRendering = false,
    pageNumIsPending = null,
    scale = 1.5,
    canvas = document.querySelector('#pdf-render'),
    ctx = canvas.getContext('2d');

// Load the document
pdfjsLib.getDocument(pdfUrl).promise.then(pdfDoc_ => {
    pdfDoc = pdfDoc_;
    document.querySelector('#page-num').textContent = `Page 1 of ${pdfDoc.numPages}`;
    renderPage(pageNum);
});

// Render the page
function renderPage(num) {
    pageIsRendering = true;
    pdfDoc.getPage(num).then(page => {
        const viewport = page.getViewport({ scale });
        canvas.height = viewport.height;
        canvas.width = viewport.width;

        const renderCtx = {
            canvasContext: ctx,
            viewport
        };

        page.render(renderCtx).promise.then(() => {
            pageIsRendering = false;

            if (pageNumIsPending !== null) {
                renderPage(pageNumIsPending);
                pageNumIsPending = null;
            }
        });

        document.querySelector('#page-num').textContent = `Page ${num} of ${pdfDoc.numPages}`;
    });
}

// Check for pages rendering
function queueRenderPage(num) {
    if (pageIsRendering) {
        pageNumIsPending = num;
    } else {
        renderPage(num);
    }
}

// Show Prev Page
document.querySelector('#prev-page').addEventListener('click', () => {
    if (pageNum <= 1) {
        return;
    }
    pageNum--;
    queueRenderPage(pageNum);
});

// Show Next Page
document.querySelector('#next-page').addEventListener('click', () => {
    if (pageNum >= pdfDoc.numPages) {
        return;
    }
    pageNum++;
    queueRenderPage(pageNum);
});

// Zoom In
document.querySelector('#zoom-in').addEventListener('click', () => {
    scale += 0.25;
    queueRenderPage(pageNum);
});

// Zoom Out
document.querySelector('#zoom-out').addEventListener('click', () => {
    if (scale <= 0.5) return;
    scale -= 0.25;
    queueRenderPage(pageNum);
});

// Download PDF
document.querySelector('#download-pdf').addEventListener('click', () => {
    window.open(pdfUrl, '_blank');
});

// Share PDF
document.querySelector('#share-pdf').addEventListener('click', () => {
    const shareUrl = `${window.location.origin}${window.location.pathname}?pdf=${encodeURIComponent(pdfUrl)}`;
    navigator.clipboard.writeText(shareUrl);
    alert('PDF link copied to clipboard!');
});
