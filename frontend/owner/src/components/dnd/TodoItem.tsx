import React, { useState, useRef, useEffect } from 'react';
import { FiX } from 'react-icons/fi';
import { BsFillCheckSquareFill, BsSquare } from 'react-icons/bs';
import { Draggable } from 'react-beautiful-dnd';
import { CardContainer } from '../style/card';
import { OrderHeaderBox, OrderMenuBox, OrderMenuText, OrderPriceText } from '../style/order';

interface Props {
	index: number;
	todo: OrderData;
	todos: OrderData[];
	inbox: OrderData[];
	completed: OrderData[];
	setTodos: (s: OrderData[]) => void;
	setInbox: (s: OrderData[]) => void;
	setCompleted: (s: OrderData[]) => void;
}

const TodoItem = ({ index, todo, todos, setTodos, inbox, completed, setInbox, setCompleted }: Props) => {
	const [edit, setEdit] = useState<boolean>(false);
	const [editTodo, setEditTodo] = useState<string>(todo.todo);

	const textareaRef = useRef<HTMLTextAreaElement>(null);

	useEffect(() => {
		textareaRef.current?.focus();
		if (textareaRef.current) {
			textareaRef.current.style.height = textareaRef.current.scrollHeight + 'px';
		}
	}, [edit]);

	const handleDone = (id: string) => {
		if (todo.isDone) {
			setTodos(todos.filter((todo) => todo.id !== id));
			setInbox([...inbox, { ...todo, isDone: false }]);
		} else {
			setTodos(todos.filter((todo) => todo.id !== id));
			setCompleted([...completed, { ...todo, isDone: true }]);
		}
	};

	const handleDelete = (id: string) => {
		setTodos(todos.filter((todo) => todo.id !== id));
	};

	const handleEdit = (e: React.FormEvent, id: string) => {
		e.preventDefault();
		setTodos(todos.map((todo) => (todo.id === id ? { ...todo, todo: editTodo } : todo)));
		setEdit(false);
	};

	const handleClickToEdit = () => {
		if (!edit && !todo.isDone) {
			setEdit(!edit);
		}
	};

	return (
		<Draggable draggableId={todo.id.toString()} index={index} key={todo.id}>
			{(provided) => (
				<CardContainer
					width={200}
					className="todo-item"
					{...provided.draggableProps}
					{...provided.dragHandleProps}
					ref={provided.innerRef}
				>
					<form className="edit-form" onSubmit={(e) => handleEdit(e, todo.id)}>
						<OrderHeaderBox>
							<OrderHeaderBox>
								<span onClick={() => handleDone(todo.id)}>
									{todo.isDone ? <BsFillCheckSquareFill /> : <BsSquare />}
								</span>
								{edit ? (
									<textarea
										className="ctetextarea"
										ref={textareaRef}
										onChange={(e) => {
											e.target.style.height = e.target.scrollHeight + 'px';
											setEditTodo(e.target.value);
										}}
										onBlur={(e) => handleEdit(e, todo.id)}
										value={editTodo}
									></textarea>
								) : (
									<span
										className={`ctetext${todo.isDone ? ' -completed' : ''}`}
										onClick={handleClickToEdit}
									>
										{todo.todo}
									</span>
								)}
							</OrderHeaderBox>
							<button type="button" className="deletebtn" onClick={() => handleDelete(todo.id)}>
								<FiX />
							</button>
						</OrderHeaderBox>
						<OrderPriceText>주문 금액 : {todo.totalPrice}</OrderPriceText>
						<OrderMenuBox>
							{todo?.menus?.map((item) => (
								<OrderMenuText>
									{item.name} x {item.quantity}
								</OrderMenuText>
							))}
						</OrderMenuBox>
					</form>
				</CardContainer>
			)}
		</Draggable>
	);
};

export default TodoItem;
