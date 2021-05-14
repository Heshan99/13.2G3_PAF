$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();


});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
		$("#alertSuccess").text("");
 		$("#alertSuccess").hide();
 		$("#alertError").text("");
 		$("#alertError").hide(); 

// Form validation-------------------
var status = validateItemForm();
	if (status != true)
 		{
 			$("#alertError").text(status);
 			$("#alertError").show();
 			return;
 		}
// If valid-------------------------
 		$("#formItem").submit();
});
// UPDATE==========================================
$(document).on("click",".btnUpdate", function(event)
{
	 	$("#hiditemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
 		$("#itemCode").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#itemPrice").val($(this).closest("tr").find('td:eq(1)').text());
		$("#itemDesc").val($(this).closest("tr").find('td:eq(2)').text());
});

// CLIENT-MODEL================================================================
function validateItemForm()
{
 
// NAME
	if ($("#itemCode").val().trim() == "")
 		{
 			return "Insert Funding Project Name.";
 		}
// PRICE-------------------------------
	if ($("#itemPrice").val().trim() == "")
 		{
 			return "Insert Amount.";
 		}
// is numerical value
var tmpPrice = $("#itemPrice").val().trim();
	if (!$.isNumeric(tmpPrice))
 		{
 			return "Insert a numerical value for Item Price.";
 		}
// convert to decimal price
 	$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));

// DESCRIPTION------------------------
	if ($("#itemDesc").val().trim() == "")
 	{
 		return "Insert Item Fund Estimated By.";
 	}
	
		return true;
}


