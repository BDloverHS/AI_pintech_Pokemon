var commonLib = commonLib ?? {};

/**
 * 메타 태그 정보 조회
 * mode - rootUrl : <meta name="rootUrl ... />
 */
commonLib.getMeta = function(mode) {
    if(!mode) return;

    const el = document.querySelector(`meta[name='${mode}']`);

    return el?.content;
};

/**
* Ajax 요청 처리
*
* @params url : 요청 주소, http[s] :외부 URL - 컨텍스트 경로는 추가 X
* @params method 요청 방식 - GET, POST, DELETE, PATCH...
* @params callback 응답 완료 후 후속 처리 콜백 함수
* @params data : 요청 데이터
* @params headers : 추가 요청 헤더
*/
commonLib.ajaxLoad = function(url, callback, method = "GET", data, headers) {
    if (!url) return;

    const { getMeta } = commonLib;

    const csrfHeader = getMeta("_csrf_header");
    const csrfToken = getMeta("_csrf");
    url = /^http[s]?:/.test(url) ? url : getMeta("rootUrl") + url.replace("/", "");

    headers = headers ?? {};
    headers[csrfHeader] = csrfToken;

    // 통일성을 위해 모두 대문자화
    method = method.toUpperCase();

    const options = {
        method,
        headers,
    }

    if (data && ['POST', 'PUT', 'PATCH'].includes(method)) { // body 쪽 데이터 추가 기능
        options.body = data instanceof FormData ? data : JSON.stringify(data);
    }

    fetch(url, options)
        .then(res => res.json())
        .then(json => {
            if (json.success) { // 응답 성공(처리 성공)
                if (typeof callback === "function") { // 콜백 함수가 정의된 경우
                    callback(json.data);
                }
                return;
            }

            alert(json.message); // 실패 시에는 alert 메세지 출력
        })
        .catch(err => console.error(err))
};

/* 관리자, 프론트, 모바일의 공통적인 js파일 */
window.addEventListener("DOMContentLoaded", function() {
    // 체크 박스 전체 토글 기능 S
    const checkAlls = document.getElementsByClassName("check-all");
    for (const el of checkAlls) {
        el.addEventListener("click", function() {
            const { targetClass } = this.dataset;
            if (!targetClass) { // 토글할 체크박스의 클래스가 설정되지 않은 경우는 진행 X
                return;
            }

            const chks = document.getElementsByClassName(targetClass);
            for (const chk of chks) {
                chk.checked = this.checked;
            }
        });
    }
    // 체크 박스 전체 토글 기능 E
});