import './Footer.css'


export default function Footer() {
    return (
        <div className='Footer--container'>
            <ul className='Footer--contact-list Footer--content'>
                <li>1234 Business Blvd., Company, CA 90210</li>
                <li>345 678 903</li>
                <li>info@ouremail.com</li>
            </ul>
            <div className='empty'></div>
            <ul className='Footer--socialMedia Footer--content'>
                <li>Facebook</li>
                <li>Twitter</li>
                <li>Instagram</li>
            </ul>
        </div>
    )
}