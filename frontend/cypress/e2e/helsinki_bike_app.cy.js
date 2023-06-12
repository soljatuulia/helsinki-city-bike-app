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
				cy.contains('A.I. Virtasen aukio', { timeout: 18000 });
			});
      
			it('descending by departure station name when clicking twice on appropriate column name', function() {
				cy.get('#departureStation').click();
				cy.get('#departureStation', { timeout: 15000 }).click();
				cy.contains('Ympyrätalo', { timeout: 15000 });
			});

			it('ascending by return station name when clicking on appropriate column name', function() {
				cy.get('#returnStation').click();
				cy.contains('A.I. Virtasen aukio', { timeout: 18000 });
			});
      
			it('descending by return station name when clicking twice on appropriate column name', function() {
				cy.get('#returnStation').click();
				cy.get('#returnStation', { timeout: 15000 }).click();
				cy.contains('Ympyrätalo', { timeout: 15000 });
			});

			it('ascending by distance when clicking on appropriate column name', function() {
				cy.get('#distance').click();
				cy.contains('0.01', { timeout: 18000 });
			});
  
			it('descending by distance when clicking twice on appropriate column name', function() {
				cy.get('#distance').click();
				cy.get('#distance', { timeout: 20000 }).click();
				cy.contains('3680.77', { timeout: 20000 });
			});
  
			it('ascending by duration when clicking on appropriate column name', function() {
				cy.get('#duration').click();
				cy.contains('0.23', { timeout: 15000 });
			});
      
			it('descending by duration when clicking twice on appropriate column name', function() {
				cy.get('#duration').click();
				cy.get('#duration', { timeout: 18000 }).click();
				cy.contains('81495.48', { timeout: 18000 });
			});
		});

		describe('filtering', function() {
			beforeEach(function() {
				cy.get('#day-dropdown').click().get('#day').contains('1').click();
				cy.get('#month-dropdown').click().get('#May').contains('May').click();
				cy.get('#filter').click();
			});

			it('filters journeys by day and month', function() {
				cy.contains('Jätkäsaarenlaituri', { timeout: 15000 });
			});

			it('resets filters when clicking reset button', function() {
				cy.get('#reset').click();
        
				cy.contains('Select Day');
			});      
		});
	});

	describe('stations page', function() {
		beforeEach(function() {
			cy.visit('http://localhost:3000/stations');
		});

		it('can be opened', function() {
			cy.contains('Search for stations by typing the name or address below.');
		});

		it('shows station details by clicking link', function() {
			cy.get('#stationDetails').contains('view more').click();
			cy.contains('Number of departures', { timeout: 18000 });
		});

		describe('search', function() {
			it('finds appropriate results', function() {
				cy.get('#searchbar').type('ympyrätalo');
				cy.contains('Porthaninrinne 1');
			});
      
			it('shows user notification when no results are found', function() {
				cy.get('#searchbar').type('pispala');
				cy.contains('No stations found');
			});
		});
	});

	describe('station form', function() {
		beforeEach(function() {
			cy.visit('http://localhost:3000/addstation');
		});

		it('can be opened', function() {
			cy.contains('Add station');
		});

		it('does not add station to db if user does not enter ID', function() {
			cy.get('#formFinnishName').type('Keskustori');
			cy.get('#formSwedishName').type('Centraltorget');
			cy.get('#formFinnishAddress').type('Keskustori 1');
			cy.get('#formSwedishAddress').type('Centraltorget 1');
			cy.get('#formFinnishCity').type('Tampere');
			cy.get('#formSwedishCity').type('Tammerfors');
			cy.get('#formOperator').type('TKL');
			cy.get('#formCapacity').type('25');
			cy.get('#formX').type('61');
			cy.get('#formY').type('24');

			cy.get('#button-add').click();

			cy.contains('Station ID is required!');
		});

		it('does not add station to db if user enters negative number as ID', function() {
			cy.get('#formId').type('-100');
			cy.get('#formFinnishName').type('Keskustori');
			cy.get('#formSwedishName').type('Centraltorget');
			cy.get('#formFinnishAddress').type('Keskustori 1');
			cy.get('#formSwedishAddress').type('Centraltorget 1');
			cy.get('#formFinnishCity').type('Tampere');
			cy.get('#formSwedishCity').type('Tammerfors');
			cy.get('#formOperator').type('TKL');
			cy.get('#formCapacity').type('25');
			cy.get('#formX').type('61');
			cy.get('#formY').type('24');

			cy.get('#button-add').click();

			cy.contains('No negative numbers allowed!');
		});
	}); 
});