var elForm, elSelectPage, elPackageHint, elTerms;
elForm = document.getElementById('formSignup');
elSelectPage = document.getElementById('package');
elPackageHint = document.getElementById('packageHint');
elTerms = document.getElementById('terms');
elTermsHint = document.getElementById('termsHint');

function packageHint(){
    var package = this.options[this.selectedIndex].value;
    if (package == 'monthly'){
        elPackageHint.innerHTML = 'Save $10 if you pay for 1 year!'; 
    } else {
        elPackageHint.innerHTML = 'Wise Choice!';
    }
}

function checkTerms(event){
    if (!elTerms.checked) {
        elTermsHint.innerHTML = 'You must agree to the terms.';
        event.preventDefault();
    }
}

//Create event listeners: submit calls checkTerms(), change calls packageHint()
elForm.addEventListener('submit', checkTerms, false);
elSelectPage.addEventListener('change', packageHint, false);