import { dataList } from './datalist.js';

// invoke the function
(function () {
    let symbol = "MSFT";
    updateData(symbol);
})();

function updateData(symbol) {

    const item = dataList[symbol];

    // define number variables
    const change = Math.round(item.Change * 100) / 100;
    const changePercent = Math.round(item.ChangePercent * 100) / 100;
    const high = Math.round(item.High * 100) / 100;
    const lastPrice = Math.round(item.LastPrice * 100) / 100;
    const low = Math.round(item.Low * 100) / 100;
    const marketCap = Math.round(item.MarketCap * 100) / 100;
    const opening = Math.round(item.Open * 100) / 100;
    const volume = Math.round(item.Volume * 100) / 100;

    // numbers to update in the UI
    document.querySelector('.change').innerHTML = convertNumberValue(change);
    document.querySelector('.change-percent').innerHTML = `(&nbsp;${convertNumberValue(changePercent)}%)`;
    document.querySelector('.last-price').innerHTML = convertNumberValue(lastPrice);
    document.querySelector('.high').innerHTML = convertNumberValue(high);
    document.querySelector('.low').innerHTML = convertNumberValue(low);
    document.querySelector('.opening').innerHTML = convertNumberValue(opening);
    document.querySelector('.volume').innerHTML = convertNumberValue(volume);
    document.querySelector('.market-cap').innerHTML = convertNumberValue(marketCap);

    // allow styling for a negative value
    styleForChangeApi(change);

    // split the name into two lines
    splitNameTwoLines(item.Name);

    // update the timestamp
    updateTimestamp(item.Timestamp);
};

function styleForChangeApi(val) {
    if (Math.sign(val)-1) {
        document.querySelector('.change').style.color = '#FF0000';
        document.querySelector('.change-percent').style.color = '#FF0000';
    } else {
        document.querySelector('.change').style.color = '#008000';
        document.querySelector('.change-percent').style.color = '#008000';
    }
}

function splitNameTwoLines(text) {
    const firstItem = text.split(' ').slice(0, -1);
    const lastItem = text.split(' ').slice(-1);

    return document.querySelector('.company-name').innerHTML = `${firstItem.join(' ')}<br>${lastItem}`;
};

function updateTimestamp(val) {
    const localeTime = new Date(val).toLocaleTimeString('en-US');

    return document.querySelector('.time-stamp').innerHTML = `As of ${localeTime}`;
};

function convertNumberValue(val) {
    return val >= 1.0e+9 ? Math.round((Math.abs(Number(val)) / 1.0e9) * 10) / 10 + "B"
        : val >= 1.0e+6 ? Math.round(Math.abs(Number(val)) / 1.0e+6 * 10) / 10 + "M"
            : val >= 1.0e+3 ? Math.round(Math.abs(Number(val)) / 1.0e+3 * 10)/ 10 + "K"
                : val;
};

// dom is arguing about a named function
document.querySelector('.get-value').addEventListener('click', function() {
    const symbolValue = document.querySelector('.symbol-value');
    const name = symbolValue.value;

    if (dataList[name].Status === "SUCCESS") {
        updateData(name);
    } else {
        alert(dataList[name].Message);
    }
});