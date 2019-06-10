import { User } from './user';

export class Kweet {
    public id: number;
    public body: string;
    public author: User;
    public createdTime: Date;
    public isActive?: boolean;

    constructor(id?: number, body?: string, author?: User) {
        this.id = id;
        this.body = body;
        this.author = author;
    }
}