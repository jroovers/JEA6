import { Kweet } from '../models/kweet';
import { User } from '../models/user';


export const KWEETS: Kweet[] = [
    new Kweet(1, "dit is een mock kweet", new User(1, "Alice")),
    new Kweet(2, "dit is nog een mock kweet", new User(1, "Alice")),
    new Kweet(3, " Ik ben gehard by den Katergeuzen en ben den beste schutter onder den Nederlandsche vlag", new User(1, "Alice")),
    new Kweet(4, "dit IS DE VIERDE gemockte kweet", new User(1, "Alice")),
    new Kweet(5, "Hoi ik ben bob", new User(2, "Bob")),
    new Kweet(6, "Vanavond lekker eten bij hizmet! Alles erop maatje?", new User(2, "Bob")),
    new Kweet(7, "Antonio is echt een code god", new User(2, "Bob")),
    new Kweet(8, "Bijna alle skills gemaxt in RS!", new User(3, "Max")),
    new Kweet(9, "Bezig met UI? Check PrimeNG voor toffe components!", new User(3, "Max"))
];