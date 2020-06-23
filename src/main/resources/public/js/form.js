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

    // todo call ajax if works
    $.ajax({
        type: "POST",
        url: "http://notifius.jplemay.com/users/lemj0601/settings",
        data: JSON.stringify(settings),
        contentType: 'application/json',
    });

    console.log(settings);
};