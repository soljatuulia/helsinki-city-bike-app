describe('Helsinki Bike App', function() {
	it('front page can be opened', function() {
		cy.visit('http://localhost:3000');
		cy.contains('Helsinki Bike App');
	});

	describe('journeys page', function() {
		beforeEach(function() {
			cy.visit('http://localhost:3000/journeys');
		});

		it('can be opened', function() {
			cy.contains('Filter journeys by date by choosing day and month below.');
		});

		describe('sorts journeys', function() {
      it('ascending by departure station name when clicking on appropriate column name', function() {
				cy.get('#departureStation').click();
				cy.contains('A.I. Virtasen aukio');
			});
      
			it('descending by departure station name when clicking twice on appropriate column name', function() {
				cy.get('#departureStation').click().click();
				cy.contains('Ympyrätalo');
			});

      it('ascending by return station name when clicking on appropriate column name', function() {
				cy.get('#returnStation').click();
				cy.contains('A.I. Virtasen aukio');
			});
      
			it('descending by return station name when clicking twice on appropriate column name', function() {
				cy.get('#returnStation').click().click();
				cy.contains('Ympyrätalo');
			});

			it('ascending by distance when clicking on appropriate column name', function() {
				cy.get('#distance').click();
				cy.contains('0.01');
			});
  
			it('descending by distance when clicking twice on appropriate column name', function() {
				cy.get('#distance').click().click();
				cy.contains('3680.77');
			});
  
			it('ascending by duration when clicking on appropriate column name', function() {
				cy.get('#duration').click();
				cy.contains('0.23');
			});
      
			it('descending by duration when clicking twice on appropriate column name', function() {
				cy.get('#duration').click().click();
				cy.contains('68197.88');
			});
		});

		it('filters journeys by day and month', function() {
			cy.get('#day-dropdown').click().get('#day').contains('1').click();
			cy.get('#month-dropdown').click().get('#May').contains('May').click();
			cy.get('#filter').click();

			cy.contains('Jätkäsaarenlaituri');
		});

	});

	describe('stations page', function() {
		beforeEach(function() {
			cy.visit('http://localhost:3000/stations');
		});

		it('can be opened', function() {
			cy.contains('Search for stations by typing the name or address below.');
		});

		it('search finds appropriate results', function() {
			cy.get('#searchbar').type('ympyrätalo');
			cy.contains('Porthaninrinne 1');
		});

		it('shows station details by clicking link', function() {
			cy.get('#stationDetails').contains('station details').click();
			cy.contains('Number of departures');
		});
	});



});