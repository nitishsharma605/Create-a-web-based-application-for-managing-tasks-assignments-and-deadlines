// TaskList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function TaskList() {
    const [tasks, setTasks] = useState([]);
    const [newTask, setNewTask] = useState({ title: '', description: '' });

    useEffect(() => {
        axios.get('http://localhost:8080/api/tasks')
            .then(response => {
                setTasks(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        axios.post('http://localhost:8080/api/tasks', newTask)
            .then(response => {
                setTasks([...tasks, response.data]);
                setNewTask({ title: '', description: '' });
            })
            .catch(error => {
                console.error(error);
            });
    };

    const handleDelete = (id) => {
        axios.delete(`http://localhost:8080/api/tasks/${id}`)
            .then(() => {
                setTasks(tasks.filter(task => task.id !== id));
            })
            .catch(error => {
                console.error(error);
            });
    };

    return (
        <div>
            <h1>Task List</h1>
            <form onSubmit={handleSubmit}>
                <input type="text"