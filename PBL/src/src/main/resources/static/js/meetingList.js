function confirmDelete(button) {
    if (button.textContent === '削除') {
        button.textContent = '確認';
    } else if (button.textContent === '確認') {
        if (window.confirm('本当に削除しますか？')) {
            const meeting = button.closest('.meeting');
            if (meeting) {
                meeting.remove();
            }
        } else {
            button.textContent = '削除'; // ユーザーがキャンセルした場合にテキストを元に戻す
        }
    }
}