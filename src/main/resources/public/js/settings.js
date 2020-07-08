const settingFormSubmitButtonID = "settings-form-submit";
const settingServiceInputClass = "settings-service-checkbox";
const settingNotificationSenderValueInputClass = "settings-notification-sender-input";

document.getElementById(settingFormSubmitButtonID).onclick = function () {
    fetch("/settings", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(getSettingsFromForm())
    }).then(response => {
        if (response.ok) {
            location.reload();
        }
    });
};

const testNotificationButtonID = "test-notification-button";
const initialSetting = getSettingsFromForm();

document.getElementById(testNotificationButtonID).onclick = function () {
    if (JSON.stringify(initialSetting) !== JSON.stringify(getSettingsFromForm())) {
        if (!window.confirm("Vous avez des changements non sauvegardés, voulez-vous continuer ?")) return;
    }

    fetch("/send-notification", {
        method: "POST"
    }).then(() => {
        alert("Notification envoyée")
    });
};

function getSettingsFromForm() {
    const enableServices = [];
    const notificationSenders = {};
    for (const serviceCheckbox of document.getElementsByClassName(settingServiceInputClass)) {
        if (serviceCheckbox.checked) {
            enableServices.push(serviceCheckbox.value);
        }
    }
    for (const notificationSenderInput of document.getElementsByClassName(settingNotificationSenderValueInputClass)) {
        if (notificationSenderInput.value && document.getElementById(notificationSenderInput.id.replace("Value", "")).checked) {
            notificationSenders[notificationSenderInput.id.replace("Value", "")] = notificationSenderInput.value
        }
    }
    return {enableServices, notificationSenders}
}