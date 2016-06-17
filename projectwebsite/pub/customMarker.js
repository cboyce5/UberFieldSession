function CustomMarker(latlng, map, args, title, description) {
	this.latlng = latlng;
	this.args = args;
	this.title = title;
	this.description = description;
	this.div = null;
	
	this.setMap(map);	
}

CustomMarker.prototype = new google.maps.OverlayView();

CustomMarker.prototype.draw = function() {
	
	var self = this;
	
	var div = this.div;

	if (!div) {

		var div_title = document.createElement('div');
		div_title.className = 'div_title';
		div_title.innerHTML= this.title;
		
		var div_description = document.createElement('div');
		div_description.className = 'div_description';
		div_description.innerHTML= this.description;

		var div_pointer = document.createElement('div');
		div_pointer.className = 'triangle-with-shadow', 'triangle-with-shadow:after';

		div = this.div = document.createElement('div');
		if (this.args.state == 1) {
			div.className = 'div_within_marker';
		} else {
			div.className = 'div_within_marker_dirty';
		}
		div.appendChild(div_pointer);
		div.appendChild(div_title);
		div.appendChild(div_description);

		this.div = div;


		if (typeof(self.args.marker_id) !== 'undefined') {
			div.dataset.marker_id = self.args.marker_id;
		}

		var self = this;

		google.maps.event.addDomListener(div, "click", function() {
			
			document.getElementById("titleTextEditor").value = self.title;
			document.getElementById("descriptionTextEditor").value = self.description;
			document.getElementById("id-value").value = self.args.marker_id;

			if (window.mobilecheck() == false && loggedIn == true) {
				var div1 = document.getElementById('right-sidebar');
				if (div1.style.display !== 'none') {
					div1.style.display = 'none';

					GeospatialAPI.getFeature(self.args.marker_id, function(feature) {
						feature.state = 1;
						GeospatialAPI.updateFeature(feature, function(feature) {
							console.log("clean");
						});
					});

				}
				else {
					div1.style.display = 'block';

					GeospatialAPI.getFeature(self.args.marker_id, function(feature) {
						feature.state = 2;
						GeospatialAPI.updateFeature(feature, function(feature) {
							console.log("dirty");
						});
					});


				}
			} else {
				console.log("currently mobile");
			}

		});
		
		var panes = this.getPanes();
		panes.overlayImage.appendChild(this.div);
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

// Set the visibility to 'hidden' or 'visible'.
CustomMarker.prototype.hide = function() {
	if (this.div) {
		// The visibility property must be a string enclosed in quotes.
		this.div.style.visibility = 'hidden';
	}
};

CustomMarker.prototype.show = function() {
	if (this.div) {
		this.div.style.visibility = 'visible';
	}
};

CustomMarker.prototype.toggle = function() {
	if (this.div) {
		if (this.div.style.visibility === 'hidden') {
			this.show();
		} else {
			this.hide();
		}
	}
};

CustomMarker.prototype.getID = function() {
	return this.args.marker_id;
};


CustomMarker.prototype.setValues = function(title, description) {
	var self = this;
	self.title = title;
	self.description = description;
	self.div.childNodes[1].innerHTML = this.title;
	self.div.childNodes[2].innerHTML = this.description;
};