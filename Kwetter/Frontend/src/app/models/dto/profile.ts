import { User } from '../user';
import { Kweet } from '../kweet';

export class Profile {
    public user: User;
    public kweets: Kweet[];
    public followers: User[];
    public following: User[]

    constructor() {
        this.user = new User();
        this.kweets = [];
        this.followers = [];
        this.following = [];
    }
}
