import React, {Component} from 'react';
import { postTransaction, getTransactions } from '../../util/APIUtils';
import './DashboardUser.css';
import { Link } from 'react-router-dom';
import { ACCESS_TOKEN } from '../../constants';

import { Form, Input, Button, Icon, notification } from 'antd';
const FormItem = Form.Item;
class DashboardUser extends Component {
    constructor(props) {
        super(props);
		
        this.state = {
            isLoading: true
        }
		this.user = this.props.currentUser;
		this.allTransactionElems = [];
        this.getTransactions = this.getTransactions.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
	    this.getTransactions();
    }
	
	handleSubmit(event) {
        event.preventDefault();

        this.setState({
            isLoading: true
        })
        
        if (!event.target.amount.value) {
            event.target.amount.value = 0.0;
        }

        var loginRequest = {
            amount: event.target.amount.value,
            customer_email: event.target.customer_email.value,
            customer_phone: event.target.customer_phone.value,
            referenceId: event.target.referenceId.value,
            username: this.user.username
        }; 
        
        let postTransactionReq = postTransaction(loginRequest);

        postTransactionReq.then(response => {
                notification.success({
                    message: 'Merchant App',
                    description: "You're successfully submited transaction",
                });

                window.location.reload(false);
            }).catch(error => {
                if(error.status === 401) {
                    notification.error({
                        message: '401',
                        description: error.message
                    });                    
                } else {
                    notification.error({
                        message: 'Merchant App',
                        description: error.message || 'Sorry! Something went wrong. Please try again!'
                    });                                            
                }
            });
    }

	getTransactions() {
	    let user = this.props.currentUser;

		if (user) {
		    let allTransactionReq = getTransactions(user.username);

            allTransactionReq.then((result) => {
                this.allTransactionElems = result;

                this.setState({
                    isLoading: false
                })
            });		
	    }
	}
	
    render() {
		const currentUser = this.props.currentUser;
		let name; 
	    if (currentUser ){
	       name=currentUser.name;
	    }
        let isAdmin = false;

        return (
            <div>
                <div><strong>MERCHANT DASHBOARD </strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hello  {name}</div>
                <div>On this page you can register transactions and check for already registered </div>
                <Form onSubmit={this.handleSubmit} className="transaction-form">
                    <FormItem>
                        amount must be biggest from 0
                        <Input
                            required="required" 						
                            type="text" pattern="[0-9]*" 
                            size="large"
                            name="amount" />                       
                    </FormItem>
                    customer_email
                    <FormItem>               
                        <Input 
                            required="required" 						
                            type="email"					
                            size="large"
                            name="customer_email"   />                                       
                    </FormItem>
                    customer_phone
                    <FormItem>               
                        <Input
                            required="required" 						
                            type="tel"						
                            size="large"
                            name="customer_phone"   pattern="^[0-9]{5,11}$"/>                                       
                    </FormItem>
                    referenceId
                    <FormItem>               
                        <Input  
                            type="text"						
                            size="large"
                            name="referenceId"   />                        
                    </FormItem>
                    
                    <FormItem>
                        <Button type="primary" htmlType="submit" size="large" className="login-form-button">Submit transaction</Button>
                    </FormItem>
                </Form>
                
                
                <div>
                    {this.state.isLoading ? (
                        <div>Loading...</div>
                    ) : (
                        <table className="transactions-table" style={{marginBottom: "34px"}}>
                            <thead>
                                <tr className="transaction-table-header" style={{border: "1px solid #000"}}>
                                    <th style={{textAlign: "center"}}>UUID</th>
                                    <th style={{textAlign: "center"}}>Reference Id</th>
                                    <th style={{textAlign: "center"}}>amount</th>
                                    <th style={{textAlign: "center"}}>Status</th>
                                    <th style={{textAlign: "center"}}>Customer email</th>
                                    <th style={{textAlign: "center"}}>Customer phone</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.allTransactionElems.map((user) => (
                                    <tr className="transaction-table-body-row" style={{border: "1px solid #000"}}>
                                        <td>{user.uuid} |</td>
                                        <td style={{textAlign: "center"}}>{user.referenceId}</td>
                                        <td style={{textAlign: "center"}}> {user.amount} </td>
                                        <td style={{textAlign: "center"}}>{user.status}</td>
                                        <td style={{textAlign: "center"}}>{user.customer_email}</td>
                                        <td style={{textAlign: "center"}}>{user.customer_phone}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    )}
                </div> 
            </div>
        );
    }
}

export default DashboardUser;
