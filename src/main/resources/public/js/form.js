const NOTIFIUS_BACKEND_URL = "";

const settingFormSubmitButtonID = "settings-form-submit";
const settingServiceInputClass = "settings-service-checkbox";
const settingNotificationSenderInputClass = "settings-notification-sender-checkbox";
const settingNotificationSenderValueInputClass = "settings-notification-sender-input";

document.getElementById(settingFormSubmitButtonID).onclick = () => {
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