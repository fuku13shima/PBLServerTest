document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('addButton').onclick = function() {
        var mtgTitle = document.getElementById('mtgTitle').value.trim(); // trim() を使って前後の空白を除去
        var mtgDate = document.getElementById('start').value;
        var mtgTime = document.getElementById('mtgTime').value;
        var titleErrorElement = document.getElementById('titleError');
        var datetimeErrorElement = document.getElementById('datetimeError');
        var defaultDate = "2024-07-01";
        var defaultTime = "12:00";

        titleErrorElement.textContent = ""; // Clear any previous title error messages
        datetimeErrorElement.textContent = ""; // Clear any previous datetime error messages

        var hasError = false;

        if (mtgTitle === "") {
            titleErrorElement.textContent = "会議名を入力してください。";
            hasError = true;
        }

        if (mtgDate === defaultDate && mtgTime === defaultTime) {
            datetimeErrorElement.textContent = "日時を選択してください。";
            hasError = true;
        }

        if (!hasError) {
            window.location.href = 'meetingMenu.html';
        } else {
            // エラーがある場合、ページのトップにスクロール
            window.scrollTo(0, 0);
        }
    };
});
