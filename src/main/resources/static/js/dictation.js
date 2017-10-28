var startTime;
var answerTime;

function rewindAudio(sec) {
	if(sec == -1) {
		$('#audio1').get(0).currentTime = 0;
	} else {
		$('#audio1').get(0).currentTime -= sec;
	}
}
function pauseAudio() {
	$('#audio1').get(0).pause();
}
function playAudio() {
	$('#audio1').get(0).play();
}
function focusTextArea() {
	$('#textarea1').focus();
}
function answer() {
	answerTime = new Date();
	// 経過時間
	var elapsedTime = answerTime - startTime;
	// 回答
	var answeredText = $('#textarea1').val();
	// 記録用オブジェクト
	var JSONdata = {
		"user": {
			"username": username
		}
		, "question": {
			id: questionId
		}
		, "elapsedTime": elapsedTime
		, "answeredText": answeredText
	};
	$.ajax({
		type : "POST",
		url : "/records",
		data : JSON.stringify(JSONdata),
		contentType : "application/json; charset=utf-8",
		crossDomain : true,
		dataType : "json",
		headers: {'X-CSRF-TOKEN': csrfToken},
		success : function(data, status, jqXHR) {
			// Nothing to do.
		},
		error : function(jqXHR, status) {
			// error handler
			console.log('fail' + jqXHR.responseText + ":" + status.code);
		}
	});

	$('#buttonAnswer').prop('disabled', true);
	$('#compare').mergely({
		cmsettings: { readOnly: true, lineNumbers: true, lineWrapping: true },
		lhs: function(setValue) {
			setValue(answeredText);
		},
		rhs: function(setValue) {
			setValue(answerText);
		}
	});
	$('#compareResult').show();
}
function next() {
	var id = parseInt(questionId);
	location.href = "/dictations/" + (questionId+1);
}

$(document).ready(function () {
	$('#compareResult').hide();

	// Event handlers.
	$('#buttonRewind').click(function() {
		rewindAudio(-1);
	});
	$('#buttonRewind5sec').click(function() {
		rewindAudio(5);
	});
	$('#buttonPause').click(function() {
		pauseAudio();
	});
	$('#buttonPlay').click(function() {
		playAudio();
	});
	$('#buttonAnswer').click(function() {
		answer();
	});
	$('#buttonNext').click(function() {
		next();
	});

	// Setup shortcuts.
	shortcut.add("Alt+F1",function() {
		rewindAudio(-1);
	});
	shortcut.add("Alt+F5",function() {
		rewindAudio(5);
	});
	shortcut.add("Alt+F6",function() {
		pauseAudio();
	});
	shortcut.add("Alt+F7",function() {
		playAudio();
	});
	shortcut.add("Alt+F8",function() {
		focusTextArea();
	});
	shortcut.add("Alt+F9",function() {
		answer();
	});
	shortcut.add("Alt+F10",function() {
		next();
	});

	startTime = new Date();
});