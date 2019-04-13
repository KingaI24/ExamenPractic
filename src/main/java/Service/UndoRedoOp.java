package main.java.Service;

import Domain.Entity;
import Repository.IRepository;

public abstract class UndoRedoOp<T extends Entity> {

        protected IRepository<T> repository;

        public UndoRedoOp(IRepository<T> repository) {
            this.repository = repository;
        }

        public abstract void doUndo();
        public abstract void doRedo();
}
