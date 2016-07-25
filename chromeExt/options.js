// Copyright (c) 2012 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// var gmail = "https://mail.google.com/mail/?extsrc=mailto&url=%s";

function setEmail() {
  if (window.localStorage == null) {
    alert('Local storage is required for changing providers');
    return;
  }

  emailTo = document.getElementById('mailTo').value;
  window.localStorage.emailTo = emailTo;
  console.log(window.localStorage.emailTo);
}

function main() {
  if (window.localStorage == null) {
    alert("LocalStorage must be enabled for changing options.");
    return;
  }

  // Default handler is checked. If we've chosen another provider, we must
  // change the checkmark.
}

document.addEventListener('DOMContentLoaded', function () {
  main();
  document.querySelector('#setBtn').addEventListener('click', setEmail);
});
