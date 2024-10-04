// JavaScript for Theme Toggle
document.addEventListener('DOMContentLoaded', function() {
    const themeSwitch = document.getElementById('theme-switch');
    const body = document.body;

    // Load theme preference from local storage
    const currentTheme = localStorage.getItem('theme');
    if (currentTheme === 'dark') {
        body.classList.add('dark');
        themeSwitch.checked = true;
    }

    // Event listener for the toggle switch
    themeSwitch.addEventListener('change', function() {
        if (themeSwitch.checked) {
            body.classList.add('dark');
            localStorage.setItem('theme', 'dark'); // Save preference to local storage
        } else {
            body.classList.remove('dark');
            localStorage.setItem('theme', 'light'); // Save preference to local storage
        }
    });
});




// Home=============
// See All Button Click Event
document.querySelector('.see-all').addEventListener('click', function() {
    alert('Load more courses...');
  });
  


// Save-----------

   // Add click event listeners to the course cards to update the preview section
   document.querySelectorAll('.course-item').forEach(card => {
    card.addEventListener('click', () => {
        // Get course data from the data-course attribute
        const courseData = JSON.parse(card.getAttribute('data-course'));

        // Update the preview section with the selected course data
        document.getElementById('preview-title').textContent = courseData.title;
        document.getElementById('preview-instructor').textContent = courseData.instructor;
        document.getElementById('preview-duration').textContent = courseData.duration;
        document.getElementById('preview-rating').textContent = courseData.rating;
        document.getElementById('preview-description').textContent = courseData.description;
        document.getElementById('preview-video').querySelector('source').src = courseData.video;

        // Reload the video to apply the new source
        document.getElementById('preview-video').load();
    });
});