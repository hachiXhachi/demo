 function showToast(title, message, duration = 5000) {
    // Create toast element
    var toastElement = $(
      '<div class="toast fade hide p-2 bg-white" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="' + duration + '">' +
      '  <div class="toast-header border-0">' +
      '    <i class="material-icons text-success me-2">check</i>' +
      '    <span class="me-auto font-weight-bold">' + title + '</span>' +
      '    <small class="text-body">' + getCurrentTime() + '</small>' +
      '    <i class="fas fa-times text-md ms-3 cursor-pointer" data-bs-dismiss="toast" aria-label="Close"></i>' +
      '  </div>' +
      '  <hr class="horizontal dark m-0">' +
      '  <div class="toast-body">' + message + '</div>' +
      '</div>'
    );

    // Append toast to container
    $('#toastContainer').append(toastElement);

    // Initialize and show toast
    var bsToast = new bootstrap.Toast(toastElement[0]);
    bsToast.show();
}



  function errorToast(title, message, duration = 5000) {
      // Create toast element
      var toastElement = $(
        '<div class="toast fade hide p-2 bg-white" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="' + duration + '">' +
        '  <div class="toast-header border-0">' +
        '    <i class="material-icons text-danger me-2">campaign</i>' +
        '    <span class="me-auto font-weight-bold">' + title + '</span>' +
        '    <small class="text-body">' + getCurrentTime() + '</small>' +
        '    <i class="fas fa-times text-md ms-3 cursor-pointer" data-bs-dismiss="toast" aria-label="Close"></i>' +
        '  </div>' +
        '  <hr class="horizontal dark m-0">' +
        '  <div class="toast-body">' + message + '</div>' +
        '</div>'
      );

      // Append toast to container
      $('#toastContainer').append(toastElement);

      // Initialize and show toast
      var bsToast = new bootstrap.Toast(toastElement[0]);
      bsToast.show();
 }

    // Function to get current time (for the "11 mins ago" placeholder)
    function getCurrentTime() {
      var now = new Date();
      var hours = now.getHours();
      var minutes = now.getMinutes();
      var ampm = hours >= 12 ? 'pm' : 'am';
      hours = hours % 12;
      hours = hours ? hours : 12; // handle midnight
      minutes = minutes < 10 ? '0' + minutes : minutes;
      var strTime = hours + ':' + minutes + ' ' + ampm;
      return strTime;
    }