$(function() {
	initProvinceList();
});

function initProvinceList(){
	var url=BASE_PATH + '/cityCascade/province';
	ajaxGet(url,'',function(data) 
			{
				$(data).each(function()
						{
					$("#provinceNo").append("<option value='" + this.provinceNo + "'>" + this.provinceName + "</option>");
						});
			},function(data)
			{
				
			});
}

