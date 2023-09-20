
//html 파일이 열리면 바로 실행되는 함수
setTotalPrice();

//제목줄의 체크박스 유무에 따라 실행되는 함수
function setCheck(){
    //제목줄 체크박스
    const checkAll = document.querySelector('#checkAll');

    //내용부에 있는 모든 체크박스
    const chks = document.querySelectorAll('.chk');
    
    if(checkAll.checked){
        chks.forEach(function(chk, idx){
            chk.checked = true;
        });
    }
    else{
        chks.forEach(function(chk, idx){
            chk.checked = false;
        });
    }

    setTotalPrice();    
}

//총 가격 세팅
function setTotalPrice(){
    //체크된 체크박스들의  모든 가격을 더해서 span태그에 넣어준다.
    //1.chk 클래스가 있는 태그 중 checked 속성이 있는 태그들 선택
    const checkedChks = document.querySelectorAll('.chk:checked');

    //2. 모든 가격의 합
    let totalPrice = 0;
    checkedChks.forEach(function(chk, idx){ 
        totalPrice += parseInt(chk.dataset.totalPrice);
    });

    //3. span 태그에 데이터 적용
    document.querySelector('#total-price').textContent = '￦' + totalPrice.toLocaleString('ko-KR');

}

//선택 삭제 버튼 클릭 시 실행
function deleteCarts(){
    //체크된 체크박스들!!
    const checkedChks = document.querySelectorAll('.chk:checked');

    //선택된 상품이 없을 경우...
    if(checkedChks.length == 0){
        alert('삭제할 상품을 선택하세요.');
        return ;
    }

    if(confirm('선택한 상품을 삭제하시겠습니까?')){
        //모든 CART_CODE를 가져갈 배열 생성
        let cartCodeArr = [];
        //cateCodeArr[0] = 1;
        //cateCodeArr.push(2);

        //삭제하고자하는 CART_CODE들!!!
        checkedChks.forEach(function(chk, idx){
            cartCodeArr[idx] = chk.value;
            //cateCodeArr.push(chk.value);
        });

        //삭제하러 이동
        location.href = `/cart/deleteCart?cartCodes=${cartCodeArr}`;
    }

}

//장바구니 상품 수량 변경
function updateCartCnt(cartCode, selectedTag, itemPrice){
    const cartCnt = selectedTag.closest('.row').querySelector('input[type="number"]').value;

    fetch('/cart/updateCartCnt', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           'cartCode' : cartCode,
           'cartCnt' : cartCnt
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
        const result = itemPrice * cartCnt;
        selectedTag.closest('td').nextElementSibling.querySelector('.result-span').textContent = '￦' + result.toLocaleString();
        selectedTag.closest('tr').querySelector('.chk').dataset.totalPrice = result;
        setTotalPrice();
        alert('수량을 변경했습니다.');
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

//선택 구매 클릭 시 실행
function buy(){
    // ----  구매하는 장바구니 품목의 CART_CODE들을 가지고 컨트롤러 간다!---//
    //1. 구매 결정한 품목에 대한 CART_CODE들을 파악
    //   체크된 체크박스의 VALUE 값
    const chks = document.querySelectorAll('.chk:checked');

    //2. CART_CODE 값들을 저장할 배열
    const cartCodeArr = [];

    for(const chk of chks){
        cartCodeArr.push(chk.value);
    }

    location.href = `/buy/insertBuy?cartCodeList=${cartCodeArr}`;

}
















