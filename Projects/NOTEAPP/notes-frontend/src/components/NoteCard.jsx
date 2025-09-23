import React from 'react';

const NoteCard = ({ note, onEdit, onDelete }) => {
  return (
    <div className="p-4 bg-white rounded-lg shadow-md">
      <h3 className="text-lg font-bold text-gray-900">{note.title}</h3>
      <p className="mt-2 text-sm text-gray-600">{note.content}</p>
      <div className="flex justify-end mt-4 space-x-2">
        <button
          onClick={() => onEdit(note)}
          className="px-3 py-1 text-sm font-medium text-white bg-yellow-500 rounded-md hover:bg-yellow-600"
        >
          Edit
        </button>
        <button
          onClick={() => onDelete(note.id)}
          className="px-3 py-1 text-sm font-medium text-white bg-red-500 rounded-md hover:bg-red-600"
        >
          Delete
        </button>
      </div>
    </div>
  );
};

export default NoteCard;
