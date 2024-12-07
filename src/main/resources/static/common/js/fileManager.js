var commonLib = commonLib ?? {};
commonLib.fileManager = {
    /**
    * 파일 업로드 처리
    *
    *
    */
    upload(files, gid, location, single, imageOnly) {
        try {
            if (!files || files.length === 0) {
                throw new Error("파일을 선택하세요");
            }

            if (imageOnly) { // 이미지만 업로드 하는 경우
                for (const file of files) {
                    if (file.type.indexOf("image/") === -1) { // 이미지가 아닌 파일의 경우
                        throw new Error("이미지 형식이 아닙니다.");
                    }
                }
            }
        } catch (err) {
            alert(err.message);
            console.error(err);
        }
    }
};

window.addEventListener("DOMContentLoaded", function() {
    const fileUploads = document.getElementsByClassName("file-upload");
    const fileEl = document.createElement("input")
    fileEl.type = 'file';

    for (const el of fileUploads) {
        el.addEventListener("click", function() {
            const {gid, location, single, imageOnly} = this.dataset;

            fileEl.gid = gid;
            fileEl.location = location;
            fileEl.imageOnly = imageOnly === 'true';
            fileEl.single = single === 'true';
            fileEl.multiple = !fileEl.single; // false - 단일 파일 선택, true - 여러 파일 선택 가능

            fileEl.click();
        });
    }

    // 파일 선택 시 - chage 이벤트 발생
    fileEl.addEventListener("change", function(e){
        // e.currentTarget, e.target
        // console.dir(e.currentTarget.files)
       const files = e.currentTarget.files;
       const {gid, location, single, imageOnly} = fileEl;

       const { fileManager } = commonLib;
       fileManager.upload(files, gid, location, single, imageOnly);
    });
});