export class User {
    public id: number;
    public username: string;
    public biography: string;
    public location: string;
    public name: string;
    public website: string;

    constructor(id?: number, username?: string) {
        this.id = id;
        this.username = username;
    }
}