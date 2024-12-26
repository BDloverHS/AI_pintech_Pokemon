/* 관리자 쪽에서의 공통적인 js파일 */
window.addEventListener("DOMContentLoaded", function() {
    const tableActions = document.getElementsByClassName("table-action");
    for (const tableAction of tableActions) {
        const { formName } = tableAction.dataset;
        const frm = document[formName];
        if (!frm) continue;

        const buttons = frm.querySelectorAll("button");

        for (const button of buttons) {
            button,addEventListener("click", function() {
                let method = "PATCH";
                if (this.classList.contains("delete")) {
                    method = "DELETE";
                }

                if (confirm(`정말 ${method === 'DELETE' ? '삭제' : '수정'}하겠습니까?`)) {
                    frm._method.value = method;
                    frm.submit();
                }
            });
        }
    }

    // 공통 양식 버튼 처리
});