import { useState } from 'react';
import { Dropdown, Button, ButtonGroup } from 'react-bootstrap';

const JourneyDateFilter = ({ onFilter }) => {
	const [selectedDay, setSelectedDay] = useState('');
	const [selectedMonth, setSelectedMonth] = useState('');

	const handleFilter = () => {
		onFilter(selectedDay, selectedMonth);
	};

	const handleReset = () => {
		setSelectedDay('');
		setSelectedMonth('');
		onFilter('','');
	};

	const handleDaySelect = (eventKey) => {
		setSelectedDay(eventKey);
	};

	const handleMonthSelect = (eventKey) => {
		setSelectedMonth(eventKey);
	};

	const renderDayOptions = () => {
		const dayOptions = [];
		for (let i = 1; i <= 31; i++) {
			dayOptions.push(
				<Dropdown.Item id='day' key={i} eventKey={String(i)}>
					{i}
				</Dropdown.Item>
			);
		}
		return dayOptions;
	};

	return (
		<div>
			<ButtonGroup>
				<Dropdown className='dropdown' onSelect={handleDaySelect}>
					<Dropdown.Toggle variant='outline-secondary' id='day-dropdown'>
						{selectedDay || 'Select Day'}
					</Dropdown.Toggle>
					<Dropdown.Menu className='dropdown-menu'>{renderDayOptions()}</Dropdown.Menu>
				</Dropdown>
				<Dropdown className='dropdown' onSelect={handleMonthSelect}>
					<Dropdown.Toggle variant='outline-secondary' id='month-dropdown'>
						{selectedMonth || 'Select Month'}
					</Dropdown.Toggle>
					<Dropdown.Menu>
						<Dropdown.Item id='May' eventKey='5'>May</Dropdown.Item>
						<Dropdown.Item id='June' eventKey='6'>June</Dropdown.Item>
						<Dropdown.Item id='July' eventKey='7'>July</Dropdown.Item>
					</Dropdown.Menu>
				</Dropdown>
			</ButtonGroup>
			<Button id='filter' className='button' variant='warning' onClick={handleFilter}>
        Filter
			</Button>
			<Button id='reset' className='button' variant='secondary' onClick={handleReset}>
        Reset
			</Button>
		</div>
	);
};

export default JourneyDateFilter;