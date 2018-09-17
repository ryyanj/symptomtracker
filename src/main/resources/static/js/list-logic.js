$(document).ready(function(){
	$('#addbtn').click(function(){
		var newitem = $('#add').val();
		var uniqid = Math.round(new Date().getTime() + (Math.random() * 100));
		$('#list').append('<li id="'+uniqid+'" class="list-group-item"><input type="button" data-id="'+uniqid+'" class="listelement" value="X" /> '+newitem+'<input type="hidden" name="listed[]" value="'+newitem+'"></li>');
		$('#add').val('');
		return false;
	});
    $('#list').delegate(".listelement", "click", function() {
		var elemid = $(this).attr('data-id');
		$("#"+elemid).remove();
    });
});