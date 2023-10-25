import React from 'react';
import { Droppable } from 'react-beautiful-dnd';
import TodoItem from './TodoItem';
import { FlexBox } from '../style/common';
import { NoOrderText, OrderBoxTitle, OrderListBox, OrderListContainer } from '../style/order';

interface Props {
	todos: OrderData[];
	setTodos: (s: OrderData[]) => void;
	inProgressTodos: OrderData[];
	setInProgressTodos: (s: OrderData[]) => void;
	completedTodos: OrderData[];
	setCompletedTodos: (s: OrderData[]) => void;
}

const TodoList = ({
	todos,
	setTodos,
	inProgressTodos,
	setInProgressTodos,
	completedTodos,
	setCompletedTodos,
}: Props) => {
	return (
		<OrderListContainer>
			<FlexBox dir="col">
				<OrderBoxTitle>결제 완료</OrderBoxTitle>
				<OrderListBox>
					<Droppable droppableId="inbox-column">
						{(provided) => (
							<div ref={provided.innerRef} {...provided.droppableProps}>
								<ul className="todo-list -inbox">
									{todos.length === 0 ? (
										<NoOrderText>주문 정보가 존재하지 않습니다</NoOrderText>
									) : (
										todos.map((todo, index) => (
											<TodoItem
												index={index}
												todo={todo}
												key={todo.id}
												todos={todos}
												setTodos={setTodos}
												inbox={todos}
												completed={completedTodos}
												setInbox={setTodos}
												setCompleted={setCompletedTodos}
											/>
										))
									)}
									{provided.placeholder}
								</ul>
							</div>
						)}
					</Droppable>
				</OrderListBox>
			</FlexBox>
			<FlexBox dir="col">
				<OrderBoxTitle>조리중</OrderBoxTitle>
				<OrderListBox>
					<Droppable droppableId="inprogress-column">
						{(provided) => (
							<FlexBox dir="row" ref={provided.innerRef} {...provided.droppableProps}>
								<ul className="todo-list -inprogress">
									{inProgressTodos.length === 0 ? (
										<NoOrderText>주문 정보가 존재하지 않습니다</NoOrderText>
									) : (
										inProgressTodos.map((todo, index) => (
											<TodoItem
												index={index}
												todo={todo}
												key={todo.id}
												todos={inProgressTodos}
												setTodos={setInProgressTodos}
												inbox={todos}
												completed={completedTodos}
												setInbox={setTodos}
												setCompleted={setCompletedTodos}
											/>
										))
									)}
									{provided.placeholder}
								</ul>
							</FlexBox>
						)}
					</Droppable>
				</OrderListBox>
			</FlexBox>
			<FlexBox dir="col">
				<OrderBoxTitle>완료</OrderBoxTitle>
				<OrderListBox>
					<Droppable droppableId="completed-column">
						{(provided) => (
							<div ref={provided.innerRef} {...provided.droppableProps}>
								<ul className="todo-list -completed">
									{completedTodos.length === 0 ? (
										<NoOrderText>주문 정보가 존재하지 않습니다</NoOrderText>
									) : (
										completedTodos.map((todo, index) => (
											<TodoItem
												index={index}
												todo={todo}
												key={todo.id}
												todos={completedTodos}
												setTodos={setCompletedTodos}
												inbox={todos}
												completed={completedTodos}
												setInbox={setTodos}
												setCompleted={setCompletedTodos}
											/>
										))
									)}
									{provided.placeholder}
								</ul>
							</div>
						)}
					</Droppable>
				</OrderListBox>
			</FlexBox>
		</OrderListContainer>
	);
};

export default TodoList;
