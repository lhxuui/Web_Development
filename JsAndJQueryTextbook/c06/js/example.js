var noteInput, noteName, textEntered, target;

noteName = document.getElementById('noteName');
noteInput = document.getElementById('noteInput');// Input for writing the note
function writeLabel(e) {
    if (!e) {
        e = window.event;
    }
    target = event.target || event.srcElement;
    textEntered = e.target.value;
    noteName.textContent = textEntered;
}

function recorderControls(e) {                   // Declare recorderControls()
    if (!e) {                                      // If event object not present
        e = window.event;                            // Use IE5-8 fallback
    }
    target = e.target || e.srcElement;             // Get the target element
    if (e.preventDefault) {                        // If preventDefault() supported
        e.preventDefault();                          // Stop default action
    } else {                                       // Otherwise
        e.returnValue = false;                       // IE fallback: stop default action
    }

    switch (target.getAttribute('data-state')) {    // Get the data-state attribute
        case 'record':                               // If its value is record
            record(target);                            // Call the record() function
            break;                                     // Exit function to where called
        case 'stop':                                 // If its value is stop
            stop(target);                              // Call the stop() function
            break;                                     // Exit function to where called
        // More buttons could go here...
    }
}


function record(target) {                        // Declare function
    target.setAttribute('data-state', 'stop');     // Set data-state attr to stop
    target.textContent = 'stop';                   // Set text to 'stop'
}

function stop(target) {
    target.setAttribute('data-state', 'record');   //Set data-state attr to record
    target.textContent = 'record';                 // Set text to 'record'
}

// record/ pause controls and functions
if (document.addEventListener) {
    document.addEventListener('click', function (e) {
        recorderControls(e);
    }, false);

    noteInput.addEventListener('input', writeLabel, false);
}
else {
    document.attachEvent('onclick', function (e) {
        recorderControls(e);
    });
    noteInput.attachEvent('onkeyup', writeLabel, false);
}