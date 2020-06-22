const NOTIFIUS_BACKEND_URL = "";

const settingFormSubmitButtonID = "settings-form-submit";
const settingServiceInputClass = "settings-service-checkbox";
const settingNotificationSenderInputClass = "settings-notification-sender-checkbox";

document.getElementById(settingFormSubmitButtonID).onclick = () => {
    const enableServices = [];
    const notificationSenders = {};
    for (const serviceCheckbox of document.getElementsByClassName(settingServiceInputClass)) {
        if (serviceCheckbox.checked) {
            enableServices.push(serviceCheckbox.value);
        }
    }
    for (const notificationSenderInput of document.getElementsByClassName(settingNotificationSenderInputClass)) {
        if (notificationSenderInput.value) {
            notificationSenders[notificationSenderInput.name] = notificationSenderInput.value
        }
    }
    const settings = { enableServices, notificationSenders }

    // todo call ajax if works
    console.log(settings);
};