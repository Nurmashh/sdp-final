let currentIndex = 0;

function showSlide(index) {
  const container = document.querySelector('.carousel-container');
  const items = document.querySelectorAll('.carousel-item');

  if (index < 0) {
    currentIndex = items.length - 1;
  } else if (index >= items.length) {
    currentIndex = 0;
  } else {
    currentIndex = index;
  }

  const translation = -currentIndex * 100 + '%';
  container.style.transform = 'translateX(' + translation + ')';
}

function prevSlide() {
  showSlide(currentIndex - 1);
}

function nextSlide() {
  showSlide(currentIndex + 1);
}

// sounds

let numberOfButtons = document.querySelectorAll(".drum").length;

for (let i = 0; i < numberOfButtons; i++) {
    document.querySelectorAll(".drum")[i].addEventListener("click", function () {
        let buttonInnerHTML = this.innerHTML;

        switch (buttonInnerHTML) {
            case "Home":
                let audio = new Audio("/src/sounds/click-button-140881 (2).mp3");
                audio.play();
                break;
            case "About":
                let kickBass = new Audio('/src/sounds/click-button-140881 (2).mp3');
                kickBass.play();
                break;
            case "Services":
                let snare = new Audio('/src/sounds/click-button-140881 (2).mp3');
                snare.play();
                break;
            case "Contact":
                let tom1 = new Audio('/src/sounds/click-button-140881 (2).mp3');
                tom1.play();
                break;
            case "Register":
                let tom2 = new Audio('/src/sounds/click-button-140881 (2).mp3');
                tom2.play();
                break;
            case "online_request":
                let tom3 = new Audio('/src/sounds/click-button-140881 (2).mp3');
                tom3.play();
                break;
            case "l":
                let tom4 = new Audio('sounds/tom-4.mp3');
                tom4.play();
                break;
            default:
                break;
        }
    });
}


// reservation

document.addEventListener('DOMContentLoaded', function () {
  var form = document.getElementById('contact');
  var successMessage = document.getElementById('success-message'); 
  form.classList.add('show');

  form.addEventListener('submit', function (event) {
    if (!validateForm()) {
      event.preventDefault();
    } else {
      successMessage.classList.add('show');
      form.reset();
    }
  });


  function validateForm() {
    var name = document.getElementById('name').value;
    var email = document.getElementById('email').value;
    var phone = document.getElementById('phone').value;
    var nservice = document.getElementById('nameOfService').value; // Corrected
    var date = document.getElementById('date').value;
    var time = document.getElementById('time').value;
    var message = document.getElementById('message').value;
    
    if (!name || !email || !phone || nservice === 'nameOfService' || !date || time === 'time' || !message) {
      alert('Please fill in all required fields.');
      return false;
    }
    return true;
  }
});