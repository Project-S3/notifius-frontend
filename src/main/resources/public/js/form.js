const settingFormSubmitButtonID = "settings-form-submit";
const settingServiceInputClass = "settings-checkbox";
document.getElementById(settingFormSubmitButtonID).onclick = () => {
    const enableServices = [];
    for (const serviceCheckbox of document.getElementsByClassName(settingServiceInputClass)) {
        if (serviceCheckbox.checked) {
            enableServices.push(serviceCheckbox.value);
        }
    }
    console.log(enableServices);
};