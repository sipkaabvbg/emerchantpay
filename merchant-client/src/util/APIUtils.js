import { API_BASE_URL, TRANSACTION_LIST_SIZE, ACCESS_TOKEN } from '../constants';

const request = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
    .then(response => 
        response.json().then(json => {
            if(!response.ok) {
                return Promise.reject(json);
            }
            return json;
        })
    );
};

export function login(loginRequest) {
    return request({
        url: API_BASE_URL + "/auth/login",
        method: 'POST',
        body: JSON.stringify(loginRequest)
    });
}


export function postTransaction(loginRequest) {
	console.log('loginRequest'+loginRequest.context);
	
    return request({
        url: API_BASE_URL + "/payment/transaction",
        method: 'POST',
        body: JSON.stringify(loginRequest)
    });
}

export function updateUser(userRequest) {
	console.log('userRequest'+userRequest.context);
	
    return request({
        url: API_BASE_URL + "/user/update",
        method: 'POST',
        body: JSON.stringify(userRequest)
    });
}

export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/user/user",
        method: 'GET'
    });
}

export function getUserProfile(username) {
    return request({
        url: API_BASE_URL + "/users/" + username,
        method: 'GET'
    });
}

export function getTransactions(username) {
    return request({
        url: API_BASE_URL + "/payment/transaction/" + username ,
        method: 'GET'
    });
}

export function getAllUsers() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/user/users",
        method: 'GET'
    });
}

export function deleteUser(username) {
    return request({
        url: API_BASE_URL + "/user/delete/" + username,
        method: 'DELETE'
    })
}