function getMovieInfo(movieid) {
	$.get('movieListing/movie/' + movieid,
					function(movie) {
						var c = '<div class="summary" id="summary"><h1>'
								+ movie.title
								+ '</h1><img width="200" height="248" src="../images/posters/'
								+ movie.posterFileName
								+ '" alt="View Trailer">' + '<p>'
								+ movie.summary + '</p>' + '<p>Genre: ';
						for ( var i = 0; i < movie.genres.length; i++) {
							c = c + movie.genres[i].genreName + ' / ';
						}
						c = c + '</p><p>Actors: '
								+ movie.actorsDisplay
								+ '</p><p>Director: '
								+ movie.directorsDisplay
								+ '</p><p>Release Date: '
								+ movie.year
								+ '</p> <p>Run Time: '
								+ movie.displayRunTime
								+ '</p><p>Rental Price: £'
								+ movie.price
								+ '</p>';
								if (movie.rented) {									
									c = c + '<a href="#" onclick="cancelReservseMovie(\''
									+ movie.movieID
									+ '\')">'
									+ '<img border="0" src="../images/reserveD.jpg" alt="View Trailer">';
								} else {
									c = c + '<a href="#" onclick="reservseMovie(\''
										+ movie.movieID
										+ '\')">'
										+ '<img border="0" src="../images/reserve.jpg" alt="View Trailer"></a>';
								}
						c = c + '</li></div><ul id="source" class="image-grid"></ul><ul id="destination" class="image-grid"></ul>';
						$('#movieFront').html(c);
					}, 'json');
}

function cancelReservseMovie(movieid) {
	$.ajax({
		url : "movieListing/cancelReservation/" + movieid,
		type : "PUT",
		success : function(){
			getMovieInfo(movieid);
		  },
		dataType : "json",
		contentType : "application/json"
	});
}

function reservseMovie(movieid) {
	$.ajax({
		url : "movieListing/storeReservation/" + movieid,
		type : "POST",
		success : function(){
			getMovieInfo(movieid);
		  },
		dataType : "json",
		contentType : "application/json"
	});
}

function getmoviesForGenre(genreid) {
	$.get('movieListing/genre/' + genreid,
					function(movies) {
						$('#summary').hide();
						var ml = '<h1>Movie Listing</h1><ul id="source" class="image-grid">';
						var l = '';
						for ( var i = 0; i < movies.length; i++) {
							l = l + '<li data-type="'
									+ genreid
									+ '" data-id="'
									+ movies[i].movieID
									+ '" class="right">' 
									+ '<img width="200" height="248" src="../images/posters/'
									+ movies[i].posterFileName
									+ '" alt="View Trailer"/><strong class="title"> <a onclick="getMovieInfo(\''
									+ movies[i].movieID + '\')"  href="#">'
									+ movies[i].title + '</a></strong></li>';
						}
						ml = ml + l;
						ml = ml + '</ul><ul id="destination" class="image-grid"></ul>';
						$('#destination').html(l);
						$('#source').quicksand($('#destination li'), {
							adjustHeight : 'dynamic'
						});
						$('#destination').hide();
					}, 'json');
}

function showMovieReservationListing(memberid) {
	$('#movieReservationTable').children().remove();
	$('#createMember').hide();
	$('#userlist').hide();
	$('#userDetails').show();
	$.get('movieListing/listAllMovieReservations/' + memberid,
					function(movieMemberReservations) {
						$('#movieReservationTable').append('<tr><th><h2>Title</h2></th><th><h2>Price</h2></th><th><h2>Date of Reservation</h2></th><th><h2>Rented</h2></th></th><th><h2>Picked Up</h2></th></tr>');
						for ( var i = 0; i < movieMemberReservations.length; i++) {
							var row = '';
							if (i % 2 == 1) {
								row = '<tr class="odd">';
							} else {
								row = '<tr class="even">';
							}
							row = row + '<td>'
									+ movieMemberReservations[i].movie.title
									+ ' </td><td> '
									+ movieMemberReservations[i].movie.price
									+ ' </td><td> '
									+ movieMemberReservations[i].reservationDate
									+ '</td><td>'
									+ movieMemberReservations[i].rented
									+ ' </td><td>';
									if (movieMemberReservations[i].rented) {
										row = row + '<img width="48" height="48" src="../images/completed.jpg"/></td>';
									} else {
										row = row + '<img onclick="rented('
										+ memberid + ',' + movieMemberReservations[i].movieReservationID
										+ ')" width="48" height="48" src="../images/rent.jpg"/></td>';
									}
									+'</tr>';
							$('#movieReservationTable').append(row);
						}
					}, 'json');
}

function rented(memID, movieid) {
	$.ajax({
		url : "movieListing/reservationRented/" + movieid + "/memberid/" + memID,
		type : "POST",
		success :  function() {
			showMovieReservationListing(memID);
		},
		dataType : "json",
		contentType : "application/json"
	});
}

function showMemberListing() {
	$('#memberTable').children().remove();
	$('#createMember').hide();
	$('#userlist').show();
	$('#userDetails').hide();
	$.get('movieListing/listAllVideoStoreMember',
					function(movieMembers) {
						$('#memberTable').append('<tr><th><h2>Member Name</h2></th><th><h2>Membership Number</h2></th><th><h2>Location</h2></th><th><h2>Total</h2></th></th><th><h2>Delete Member</h2></th></tr>');
						for ( var i = 0; i < movieMembers.length; i++) {
							var row = '';
							if (i % 2 == 1) {
								row = '<tr class="odd">';
							} else {
								row = '<tr class="even">';
							}
							row = row + '<td><span onmouseover="changeCursor(this,\'pointer\');" onmouseout="changeCursor(this,\'default\');" class="link" onclick="showMovieReservationListing('
									+ movieMembers[i].videoStoreMemberID
									+ ')">'
									+ movieMembers[i].name
									+ '</td><td>'
									+ movieMembers[i].memebershipNumber
									+ '</td><td> '
									+ movieMembers[i].location
									+ '</td><td>'
									+ movieMembers[i].account.total
									+ '</td><td>'
									+ '<img onclick="deleteUser('
									+ movieMembers[i].videoStoreMemberID
									+ ')" width="48" height="48" src="../images/delete.jpg"/></td></tr>';
							$('#memberTable').append(row);
						}
					}, 'json');
}

function changeCursor(el, cursor) {
	el.style.cursor = cursor;
}

function deleteUser(videoStoreMemberID) {
	$.ajax({
		url : "movieListing/deleteVideoStoreMember/" + videoStoreMemberID,
		type : "DELETE",
		success : function() {
			showMemberListing();
		  },
	});
}

function showMember() {
	$('#memberTable').children().remove();
	$('#createMember').show();
	$('#userlist').hide();
	$('#userDetails').hide();
}

function createMember() {
	$('#createMember').show();
	$('#userlist').hide();
	$('#userDetails').hide();
	var deviceJSON = JSON.stringify({
		name : $('#name').val(),
		location : $('#loc').val(),
		memebershipNumber : $('#mem').val(),
		user : {
			password : $('#pwd').val(),
			username : $('#name').val()
		}
	});

	$.ajax({
		url : "movieListing/createVideoStoreMember",
		type : "POST",
		data : deviceJSON,
		success : function() {
			showMemberListing();
		  },
		dataType : "json",
		contentType : "application/json"
	});
}

function reset() {
	$('#mem').val('');
	$('#loc').val('');
	$('#name').val('');
	$('#pwd').val('');
}