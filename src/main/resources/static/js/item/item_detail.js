///////////////////////////////////////////////
// -------------- 함수 영역 ----------------//
///////////////////////////////////////////////


//장바구니 버튼 클릭 시 로그인 여부 체크
function isLogin(loginInfo, itemCode){
    if(loginInfo == null){
        if(confirm('로그인 후 사용가능합니다.\n로그인 하시겠습니까?')){
            location.href = '/member/loginForm';
        }
    }
    else{
        fetch('/cart/insertCartFetch', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                'itemCode' : itemCode,
                'cartCnt' : document.querySelector('input[type="number"]').value
            })
        })
        .then((response) => {
            if(!response.ok){
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return ;
            }
        
            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            if(data == 1){
                if(confirm('상품이 장바구니에 담겼습니다.\n장바구니 목록으로 이동할까요?')){
                    location.href = '/cart/list';
                }
            }
            else{
                alert('일시적인 오류가 발생했습니다.\n관리자에서 문의하세요.');
            }
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }

}

///////////////////////////////////////////////
// -------------- 이벤트 영역 ----------------//
///////////////////////////////////////////////

//상품 수량이 변경될때 마다 실행되는 이벤트
const cartCntInput = document.querySelector('input[type="number"]');
cartCntInput.addEventListener('input', function(event){
    //event.target -> 현재 이벤트가 진행되는 태그
    //event.target.value;
    // console.log(event.target.value);
    // console.log(event.target.dataset);
    // console.log(event.target.dataset.itemPrice);

    if(event.target.value == ''){
        event.target.value = 1;
    }

    //단가
    const itemPrice = event.target.dataset.itemPrice;

    //수량
    const cnt = event.target.value;

    const result = itemPrice * cnt;

    document.querySelector('input[name="buyPrice"]').value = result;
    
    document.querySelector('#total-price-span').textContent = '￦' + result.toLocaleString('ko-KR');


});
