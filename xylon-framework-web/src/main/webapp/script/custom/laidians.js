window.laidians = (function(){
	function Laidians(els){
	}
	
	var laidians = {
		get: function (selector) {
			var els;
			if (typeof selector === "string") {
		    	els = document.querySelectorAll(selector);
			} else if (selector.length) {
				els = selector;
			} else {
				els = [selector];
			}
			return new Laidians(els);
		}
	}
	return laidians;
}());