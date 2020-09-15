window.onload = function () {
    Vue.component('login-modal', {
        template:
            `
        <div class="modal">
            <div class="modal-content">
                <span class="close" v-on:click="">&times;</span>
                <form>
                    <input type="text" placeholder="Enter the user name">
                    <input type="password" placeholder="Enter the password">
                    <button type="submit">Log in</button>
                </form>
            </div>
        </div>
        `,
        method: {
           
        }
    });
    Vue.component('todo-item', {
        props: ['todo', 'index'],
        template: `
    <li>
        <span class="dot"></span>
        <input type="checkbox" v-on:change="updateStatus(todo)" :checked="todo.completed">
        <label  v-bind:class="[todo.completed ? 'completed' : '']">{{ todo.data }}</label>
        <input type = "text"  value ="todo.data"></input>
        <button v-on:click="" class="btn">Edit</button>
        <button v-on:click="remove(index)" class="btn">x</button>
    </li>
    `,
        methods: {
            remove: function (index) {
                this.$emit('remove');
            },
            updateStatus: function (todo) {
                todo.completed = !todo.completed;
            },

        }
    });

    var app = new Vue({
        el: '#app',
        data: {
            newTodoText: '',
            hided: false,
            todoData: [],
            loginModal: false
        },
        created() {
            window.addEventListener('beforeunload', this.handler);
        },
        computed: {
            list: function () {
                return this._getTodos(false);
            },
            clist: function () {
                if (!this.hided)
                    return this._getTodos(true);
            }
            ,
            allCount: function () {
                var _this = this;
                return Object.keys(this.todoData).length;
            },
            completedCount: function () {
                var _this = this;
                return Object.keys(this.todoData).filter(function (value) {
                    return _this.todoData[value].completed
                }).length;
            },
            incompleteCount: function () {
                var _this = this;
                return Object.keys(this.todoData).filter(function (value) {
                    return !_this.todoData[value].completed
                }).length;
            },
        },
        beforeCreate: async function () {
            this.todoData = await fetch("http://localhost:80/api/getList")
                .then(response => response.json());
        },
        methods: {
            openModal: function () {
                loginModal = true;
            },
            handler: function (event) {
                var xhr = new XMLHttpRequest();
                var url = "http://localhost:80/api";
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.send(JSON.stringify(this.todoData));
            },
            hide: function () {
                this.hided = !this.hided;
            },
            add: function () {
                if (this.todoData.length != 0)
                    var id = this.todoData.length;
                else
                    id = 0;
                Vue.set(this.todoData, id, {
                    id: id,
                    data: this.newTodoText,
                    status : "normal",
                    completed: false,
                    userID:id
                });
                this.newTodoText = '';
            },
            clearAll: function () {
                this.todoData = [];
                this.newTodoText = '';
            },
            del: function (index) {
                Vue.delete(this.todoData, index);
            },
            _getTodos: function (isCompleted) {
                var list = {};
                for (var index in this.todoData) {
                    if (this.todoData[index].completed === isCompleted) {
                        list[index] = this.todoData[index];
                    }
                }
                return list;
            },
        }
    });
}