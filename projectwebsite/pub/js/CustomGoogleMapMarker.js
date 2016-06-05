

function CustomMarker(latlng, map, args, title, description, div) {
	this.latlng = latlng;
	this.args = args;
	this.title = title;
	this.description = description;
	//this.div = div;
	
	this.setMap(map);	
}

CustomMarker.prototype = new google.maps.OverlayView();

CustomMarker.prototype.draw = function() {
	
	var self = this;
	
	var div = this.div;

	if (!div) {

		var div_title = document.createElement('div');
		div_title.className = 'div_title';
		div_title.innerHTML= this.title
		/*
		div_title.style.position = 'absolute';
		div_title.style.cursor = 'pointer';
		div_title.style.width = '200px';
		div_title.style.height = '30px';
		div_title.style.fontSize = '18px';
		div_title.style.top = '5px';
		div_title.style.left = '5px';
		div_title.innerHTML= this.title
		div_title.style.textAlign = 'center';
		//div_title.style.border = '3px solid black';
		*/
		
		var div_description = document.createElement('div');
		div_description.className = 'div_description';
		div_description.innerHTML= this.description;
		/*
		div_description.style.position = 'absolute';
		div_description.style.cursor = 'pointer';
		div_description.style.width = '200px';
		div_description.style.height = '70px';
		div_description.style.top = '35px';
		div_description.style.left = '5px';
		div_description.innerHTML= this.description;
		//div_description.style.border = '3px solid black';
*/
		var div_pointer = document.createElement('div');
		div_pointer.className = 'triangle-with-shadow', 'triangle-with-shadow:after';
		/*
		div_pointer.style.position = 'absolute';
		div_pointer.style.width = '';
		div_pointer.style.height = '';
		div_pointer.style.borderStyle = 'solid';
		div_pointer.style.top = '100px';
		div_pointer.style.left = '88px';
		div_pointer.style.borderColor = 'white transparent transparent transparent';
		div_pointer.style.borderWidth = '25px 12px 0px 12px';
		div_pointer.style. = '4px 10px 10px -6px #000000'
		*/

		div = this.div = document.createElement('div');
		div.className = 'div_within_marker';

		div.appendChild(div_pointer);
		div.appendChild(div_title);
		div.appendChild(div_description);

		
		if (typeof(self.args.marker_id) !== 'undefined') {
			div.dataset.marker_id = self.args.marker_id;
	}

	google.maps.event.addDomListener(div, "click", function(event) {
		alert('You clicked on a custom marker!');
		google.maps.event.trigger(self, "click");
	});

	var panes = this.getPanes();
		panes.overlayImage.appendChild(div);
	}
	
	var point = this.getProjection().fromLatLngToDivPixel(this.latlng);
	
	if (point) {
		div.style.left = (point.x - 100) + 'px';
		div.style.top = (point.y - 125) + 'px';
	}
};

CustomMarker.prototype.remove = function() {
	if (this.div) {
		this.div.parentNode.removeChild(this.div);
		this.div = null;
	}	
};

CustomMarker.prototype.getPosition = function() {
	return this.latlng;	
};

