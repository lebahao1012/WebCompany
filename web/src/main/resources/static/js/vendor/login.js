const formOpenBtn = document.querySelector("#form-open"),
  home = document.querySelector(".home"),
  formContainer = document.querySelector(".form_container"),
  formCloseBtn = document.querySelector(".form_close"),
  signupBtn = document.querySelector("#signup"),
  loginBtn = document.querySelector("#login"),
  pwShowHide = document.querySelectorAll(".pw_hide"),
  loginForm = document.querySelector(".login_form"),
  signupForm = document.querySelector(".signup_form"),
  logoutBtn = document.querySelector("#logout"),
  userInfo = document.querySelector("#user-info");

// Function to update UI after successful login
function updateUIAfterLogin(user) {
  userInfo.textContent = user.name;
  userInfo.style.display = "inline-block";
  logoutBtn.style.display = "inline-block";
  loginForm.style.display = "none";
  signupForm.style.display = "none";
}

formOpenBtn.addEventListener("click", () => home.classList.add("show"));
formCloseBtn.addEventListener("click", () => home.classList.remove("show"));

pwShowHide.forEach((icon) => {
  icon.addEventListener("click", () => {
    let getPwInput = icon.parentElement.querySelector("input");
    if (getPwInput.type === "password") {
      getPwInput.type = "text";
      icon.classList.replace("uil-eye-slash", "uil-eye");
    } else {
      getPwInput.type = "password";
      icon.classList.replace("uil-eye", "uil-eye-slash");
    }
  });
});

signupBtn.addEventListener("click", (e) => {
  e.preventDefault();
  formContainer.classList.add("active");
});
loginBtn.addEventListener("click", (e) => {
  e.preventDefault();
  formContainer.classList.remove("active");
});

loginForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  const emailInput = loginForm.querySelector("input[type='email']");
  const passwordInput = loginForm.querySelector("input[type='password']");

  try {
    const response = await fetch("/api/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: emailInput.value,
        password: passwordInput.value
      })
    });

    if (response.ok) {
      const user = await response.json();
      updateUIAfterLogin(user);
    } else {
      throw new Error("Email or password not valid. Please try again.");
    }
  } catch(error) {
    alert(error.message);
  }
});

logoutBtn.addEventListener("click", (e) => {
  e.preventDefault();
  userInfo.style.display = "none";
  logoutBtn.style.display = "none";
  loginForm.style.display = "block";
  signupForm.style.display = "none";
});

// Function to reset UI state after logging out
function resetUIState() {
  userInfo.style.display = "none";
  logoutBtn.style.display = "none";
  loginBtn.style.display = "inline-block";
  loginForm.style.display = "block";
  signupForm.style.display = "none";
  formOpenBtn.style.display = "inline-block";

  const toggleFormDisplay = (formToShow, formToHide) => {
    formContainer.classList.add("active");
    formToShow.style.display = "block";
    formToHide.style.display = "none";
  };

  signupBtn.addEventListener("click", (e) => {
    e.preventDefault();
    toggleFormDisplay(signupForm, loginForm);
  });

  loginBtn.addEventListener("click", (e) => {
    e.preventDefault();
    toggleFormDisplay(loginForm, signupForm);
  });
}

// Function to show the contact page after successful login
function showContactPage() {
  home.classList.add("show");
} 

// Function to show the contact page after successful login
function showContactPage() {
  const contactPage = document.querySelector(".contact-page");
  contactPage.style.display = "block";
}

// Function to update UI after successful login
function updateUIAfterLogin(user) {
  userInfo.textContent = user.name;
  userInfo.style.display = "inline-block";
  userInfo.style.color = "ghostwhite";
  userInfo.style.margin = "16px";
  logoutBtn.style.display = "inline-block";
  loginBtn.style.display = "none";
  loginForm.style.display = "none";
  signupForm.style.display = "none";
  home.classList.remove("show");
  formOpenBtn.style.display = "none";
  
  showContactPage();
}

signupForm.addEventListener('submit', async (event) => {
  event.preventDefault();
  
  const usernameInput = signupForm.querySelector('input[name="username"]');
  const emailInput = signupForm.querySelector('input[name="email"]');
  const passwordInput = signupForm.querySelector('input[name="password"]');

  try {
    const response = await fetch('/api/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: usernameInput.value,
        email: emailInput.value,
        password: passwordInput.value
      })
    });

    if (response.ok) {
      const user = await response.json();
      updateUIAfterRegistration(user);
    } else {
      throw new Error('Registration failed. Please try again.');
    }
  } catch (error) {
    alert(error.message);
  }
});

function updateUIAfterRegistration(user) {
  alert(`Registration successful for user ${user.username}! Please get back to the home page in order to login.`);
  window.location.href = '/'; // chuyển hướng người dùng đến trang chủ
}

logoutBtn.addEventListener("click", (e) => {
  e.preventDefault();
  resetUIState();
});

// Show the success contact
document.addEventListener("DOMContentLoaded", function() {
  // Get the button element by its ID
  const btnContactUs = document.getElementById("btnContactUs");
  const nameInput = document.querySelector('input[name="name"]');
  const emailInput = document.querySelector('input[name="email"]');
  const phoneInput = document.querySelector('input[name="phone"]');
  const companyInput = document.querySelector('input[name="company"]');
  const messageInput = document.querySelector('textarea[name="message"]');

  // Add a click event listener to the button
  btnContactUs.addEventListener("click", async function() {
    try {
      // Make an asynchronous HTTP POST request to the server
      const response = await fetch('/api/contacts', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          name: nameInput.value,
          email: emailInput.value,
          phone: phoneInput.value,
          company: companyInput.value,
          message: messageInput.value
        })
      });

      if (response.ok) {
        // Show the alert when the message is successfully sent
        alert("Send message successful");
      } else {
        throw new Error('Sending message failed. Please try again.');
      }
    } catch (error) {
      // Show an error alert if something goes wrong with the request
      alert(error.message);
    }
  });
});




