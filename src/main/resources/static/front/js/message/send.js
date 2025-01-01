window.addEventListener("DOMContentLoaded", function() {
    // ClassicEditor : ckeditor5에 있는 핵심적인 클래스로 구성된 생성자 객체
    // ClassicEditor.create(document.getElementById("content"))
    //    .then((editor) => console.log(editor));

    const { loadEditor } = commonLib;

    loadEditor('content', 350)
        .then((editor) => {
            window.editor = editor; // 전역 변수로 등록, then 구간 외부에서도 접근 가능하게 처리
        });
});