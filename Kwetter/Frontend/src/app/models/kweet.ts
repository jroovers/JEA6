import { User } from './user';

export class Kweet {
    public id: number;
    public body: string;
    public author: User;

    constructor(id: number, body: string, author: User) {
        this.id = id;
        this.body = body;
        this.author = author;
    }
}