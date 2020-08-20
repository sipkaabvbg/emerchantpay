import React, {Component} from 'react';

import { getAllUsers, deleteUser, updateUser } from '../../util/APIUtils';
import { Form, Input, Button, notification } from 'antd';
import './DashboardAdmin.css';
const FormItem = Form.Item;

class DashboardAdmin extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isLoading: true,
            showEditUserForm: false
        }

        this.allUsersElems = [];
        this.getUsers = this.getUsers.bind(this);
        this.deleteUser = this.deleteUser.bind(this);

        this.userEditInfo = {
            username: null,
            name: null,
            email: null,
            description: null,
            password: null,
            role: null,
            status: null,
            total_transaction_sum: null
        }

        this.openEditUserForm = this.openEditUserForm.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
    }

    componentDidMount() {
        this.getUsers();
		
    }

    getUsers() {
        let allUsersReq = getAllUsers();

        allUsersReq.then((result) => {
            this.allUsersElems = result;
            console.log(this.allUsersElems);
            this.setState({
                isLoading: false
            })
        });
    }

    deleteUser(username) {
 
        let deleteUserReq = deleteUser(username);

        deleteUserReq.then((result) => {
            window.location.reload(false);
        }).catch(error => {
            notification.error({
                message: 'Merchant App',
                description: error.message || 'Sorry! Something went wrong. Please try again!'
            });
            window.location.reload(false);                                    
        });
    }

    openEditUserForm(user) {
        this.setState({
            showEditUserForm: false
        })

        this.userEditInfo = user;

        this.setState({
            showEditUserForm: true
        })

        console.log(this.userEditInfo);
    }

    handleEdit(event) {
        event.preventDefault();

       if (!event.target.total_transaction_sum.value) {
            event.target.total_transaction_sum.value = 0.0;
        }
        let userRequest = {
            username: this.userEditInfo.username,
            name: event.target.name.value,
            email: event.target.email.value,
            description: event.target.description.value,
            password: event.target.password.value,
            role: event.target.role.value,
            status: event.target.status.value,
            total_transaction_sum: event.target.total_transaction_sum.value
        }; 

        console.log(userRequest)

        let updateUserReq = updateUser(userRequest);

        updateUserReq.then(response => {
                notification.success({
                    message: 'Merchant App',
                    description: "You're successfully updated user",
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

    render() {

        return (
            <div>
                <div style={{textAlign: "center", fontSize: "34px"}}>DASHBOARD FOR <strong>ADMIN</strong></div>

                {this.state.isLoading ? (
                    <div>Loading...</div>
                ) : (
                    <div>
                        <table className="users-table" style={{marginBottom: "34px"}}>
                            <thead>
                                <tr className="user-table-header" style={{borderBottom: "1px solid #000"}}>
                                    <th>Username</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Role</th>
                                    <th>Status</th>
                                    <th>Description</th>
                                    <th>Total transaction sum</th>
                                    <th>Password</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.allUsersElems.map((user) => (
                                    <tr className="user-table-body-row" style={{borderBottom: "1px solid #000"}}>
                                        <td>{user.username}</td>
                                        <td>{user.name}</td>
                                        <td>{user.email}</td>
                                        <td>{user.role}</td>
                                        <td style={{textAlign: "center"}}>{user.status}</td>
                                        <td>{user.description}</td>
                                        <td style={{textAlign: "center"}}>{user.total_transaction_sum}</td>
                                        <td>{user.password}</td>
                                        <td>
                                            <button onClick={() => {this.openEditUserForm(user)}} style={{backgroundColor: "#00ff00", width: "70px", border: "0", borderRadius: "3px", cursor: "pointer", margin: "5px 0"}}>Edit</button>
                                            <button onClick={() => {this.deleteUser(user.username)}} style={{backgroundColor: "#ff0000", width: "70px", border: "0", borderRadius: "3px", cursor: "pointer", margin: "5px 0", color: "#ffffff"}}>Delete</button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>

                        {this.state.showEditUserForm ? (
                            <div className="user-popup-wrapper">
                                <div className="user-popup">
                                    <div style={{textAlign: "center"}}>EDIT USER -> <strong>{this.userEditInfo.username}</strong></div>
                                    <Form onSubmit={this.handleEdit} className="edit-user-form">
                                        <FormItem>
                                            Name
                                            <Input 
											    required="required" 
                                                type="text" 
                                                size="large"
                                                name="name" 
                                                defaultValue={this.userEditInfo.name ? this.userEditInfo.name : null} />
                                        </FormItem>
                                        <FormItem>
                                            email
                                            <Input 
											    required="required" 
                                                type="text"
                                                size="large"
                                                name="email"
                                                defaultValue={this.userEditInfo.email ? this.userEditInfo.email : null} />   
                                        </FormItem>
                                        <FormItem>
                                            description
                                            <Input 
											    required="required" 
                                                type="text" 
                                                size="large"
                                                name="description" 
                                                defaultValue={this.userEditInfo.description ? this.userEditInfo.description : null} />                 
                                        </FormItem>
                                        <FormItem>
                                            password
                                            <Input 
											    required="required" 
                                                type="text"
                                                size="large"
                                                name="password" 
                                                defaultValue={this.userEditInfo.password ? this.userEditInfo.password : null} />                 
                                        </FormItem>
                                        <FormItem>
                                            role
                                            <Input 
											    required="required" 
                                                type="text"
                                                size="large"
                                                name="role" 
                                                defaultValue={this.userEditInfo.role ? this.userEditInfo.role : null} />                 
                                        </FormItem>
                                        <FormItem>
                                            status
                                            <Input 
											    required="required" 
                                                type="text"
                                                size="large"
                                                name="status" 
                                                defaultValue={this.userEditInfo.status ? this.userEditInfo.status : 0} />                 
                                        </FormItem>
                                        <FormItem>
                                            total_transaction_sum
                                            <Input 
											    required="required" 
                                                type="text"
                                                size="large"
                                                name="total_transaction_sum" 
                                                defaultValue={this.userEditInfo.total_transaction_sum ? this.userEditInfo.total_transaction_sum : 0} />                 
                                        </FormItem>

                                        <FormItem>
                                            <Button type="primary" htmlType="submit" size="large" className="login-form-button">Submit user</Button>
                                        </FormItem>
                                    </Form>
                                </div>
                            </div>
                        ) : null}
                    </div>
                )}
            </div>
        )
    }
}

export default DashboardAdmin;