import { useSelector } from 'react-redux';

import '../custom.css';

const Notification = () => {
	const notification = useSelector(({notification}) => notification);

	if (!notification) return null;

	return (
		<div className='notification'>
			{notification}
		</div>
	);
};

export default Notification;