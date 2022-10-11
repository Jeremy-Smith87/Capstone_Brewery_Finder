import brewerDisplay from '../../Resources/brewersDisplay.jpg'
import beerLoversDisplay from '../../Resources/beerLoversDisplay.jpg'
import './RegisterHome.css'




export default function RegisterHome() {
    return (
        <div className='RegisterHome--container'>
            
            <div className='Header--color-bar'></div>
            <div className='RegisterHome--content-container'>
                <img className='RegisterHome--content-displays RegisterHome--content-beerImg' src={beerLoversDisplay} />
                <p className='RegisterHome--content-beerTxt'>Register as a beer lover to browse breweries and rate<br/>
                    your favorite beers</p>
                <p className='RegisterHome--content-beerBtn'>I'M A BEER LOVER</p>
                <img className='RegisterHome--content-displays RegisterHome--content-brewerImg' src={brewerDisplay} />
                <p className='RegisterHome--content-brewerTxt'>Register as a brewery to list your brewery and list your<br/>
                    selection of brews
                </p>
                <p className='RegisterHome--content-brewerBtn'>I'M A BREWER</p>
            </div>



           
        </div>
    )
}