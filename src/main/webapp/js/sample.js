// Function to delete or cancel one Admin record from database:
function deleteAdmin()
{
	alert("This Admin will be deleted permanently!!");
}

// Function to delete or cancel one student record from database:
function deleteStud(sId)
{
	var v=confirm("Are you sure?\nThis student record will be deleted permanently!!");
	if(v)
	{
		window.location="<%=request.getContextPath()%>/delete?id="+sId;
	}
}

// Function to edit one student record in table:
function editStud(sId)
{
	window.location="<%=request.getContextPath()%>/editForm?id="+sId;
}

// User not to visit previous page using back arrow of browser(function used to prevent this):
function preback() 
{
	window.history.forward();
} 
setTimeout("preback()",0); 
window.onunload=function()
{
	null
};

/*
function add()
{
	alert("One student will be added in the List!");
}

function update()
{
	alert("One student record will be updated !");
}
*/
