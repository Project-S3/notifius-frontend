const settingFormSubmitButtonID = "settings-form-submit";
const settingServiceInputClass = "settings-service-checkbox";
const settingNotificationSenderValueInputClass = "settings-notification-sender-input";

document.getElementById(settingFormSubmitButtonID).onclick = function() {
    const enableServices = [];
    const notificationSenders = {};
    for (const serviceCheckbox of document.getElementsByClassName(settingServiceInputClass)) {
        if (serviceCheckbox.checked) {
            enableServices.push(serviceCheckbox.value);
        }
    }
    for (const notificationSenderInput of document.getElementsByClassName(settingNotificationSenderValueInputClass)) {
        if (notificationSenderInput.value && document.getElementById(notificationSenderInput.id.replace("Value","")).checked) {
            notificationSenders[notificationSenderInput.id.replace("Value","_SENDER")] = notificationSenderInput.value
        }
    }
    const settings = { enableServices, notificationSenders }

    fetch("/settings", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(settings)
    }).then(response => {
        if (response.ok) {
            location.reload();
        }
    });
 };

const testNotificationButtonID = "test-notification-button";

document.getElementById(testNotificationButtonID).onclick = function () {
    fetch("/send-notification", {
        method: "POST"
    }).then(() => {
        alert("Notification envoyée")
    });
};