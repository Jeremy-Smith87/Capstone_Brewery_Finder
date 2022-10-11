import { useState, useEffect, useRef } from 'react'
import axios from '../API/Axios';
import './AdminAddBrewery.css'

const ADD_BREWERY_URL = '/addBrewery';

export default function AdminAddUser() {

    const [breweryFormData, setBreweryFormData] = useState({
        name: "",
        address: "",
        city: "",
        state_abbr: "",
        zipcode: "",
        phone_number: "",
        description: "",
        logo_url: "",
        hours: "",
        website: ""
    })
    console.log(breweryFormData)
    function handleChange(event) {
        setBreweryFormData(prevFormData => {
            return {
                ...prevFormData,
                [event.target.name]: event.target.value
            }
        })
    }

    

    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);
    
    const errRef = useRef();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(ADD_BREWERY_URL,
                JSON.stringify({ breweryFormData}),
                {
                    headers: { 
                        'Access-Control-Allow-Origin': 'http://localhost:8080',
                        'Access-Control-Allow-Credentials': true,
                        'Content-Type': 'application/json' },
                }
            );
            console.log(JSON.stringify(response?.data));
            setSuccess(true);
            //clear state and controlled inputs
            // setBreweryName('');
            // setOperationHours('');
            // setStreetAddress('');
            // setCity('');
            // setState('');
            // setZip('');
            // setHistory('');
            // setPhoneNumber('');
            // setLogoURL('');
            // setWebsiteURL('');
        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            } else {
                setErrMsg('Brewery Creation Failed')
            }
            errRef.current.focus();
        }
    }
    


    return (
      <div className="AdminAddBrewery--container">
        
        <div className="Header--color-bar"></div>
        <h3>Add A Brewery</h3>
        <form className="AdminAddBrewery--form-container">
          <div className="AdminAddBrewery--form-left">
            <label className="form-left" htmlFor="breweryName">
              Brewery Name<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-left"
              type="text"
              id="breweryName"
              name="name"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            {/* leave this out for now. can be added to backend */}
            {/* <label className='form-left' htmlFor="brewersName">Brewer's Name<span className='star'>*</span></label><br/>
                    <input className='form-left' 
                    type="text" 
                    id="brewersName" 
                    name='brewersName'
                    autocomplete='off'
                    onChange={(e) => setBrewerName(e.target.value)}
                    /><br/> */}

            <label className="form-left" htmlFor="address">
              Street Address<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-left"
              type="text"
              id="address"
              name="address"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-left" htmlFor="city">
              City<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-left"
              type="text"
              id="city"
              name="city"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-left" htmlFor="state">
              State<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-left"
              type="text"
              id="state"
              name="state_abbr"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-left" htmlFor="zip">
              Zip<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-left"
              type="text"
              id="zip"
              name="zipcode"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-right" htmlFor="phoneNumber">
              Phone Number<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-right"
              type="text"
              id="phoneNumber"
              name="phone_number"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />
          </div>
          <div className="AdminAddBrewery--form-right">
            <label className="form-right" htmlFor="history">
              Brewery history<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-right"
              type="text"
              id="history"
              name="description"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-right" htmlFor="hours">
              Hours of Operation<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-right"
              type="text"
              id="hours"
              name="hours"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            {/* currently omitting */}
            {/* <input className='form-right' 
                        type="radio" 
                        id="active" 
                        name='active'
                        /><span>  Active</span><br/>

                    
                    <input className='form-right' 
                        type="radio" 
                        id="inactive" 
                        name='inactive'
                        /><span>  Inactive</span><br/> */}

            <label className="form-right" htmlFor="logoURL">
              Please Give a Link to Your Logo<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-right"
              type="text"
              id="logoURL"
              name="logo_url"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            <label className="form-right" htmlFor="websiteURL">
              Please Give a Link to Your Website<span className="star">*</span>
            </label>
            <br />
            <input
              className="form-right"
              type="text"
              id="websiteURL"
              name="website"
              autoComplete="off"
              onChange={handleChange}
            />
            <br />

            {/* replacing with image url entry for now */}
            {/* <input className='form-right imageUpload' 
                        type="file" 
                        id="imageUpload" 
                        name='imageUplad'
                        /><br/> */}
          </div>

          <button
            className="AdminAddBrewery--form-submitBtn"
            onClick={handleSubmit}
          >
            SUBMIT
          </button>
        </form>
        
      </div>
    );
}