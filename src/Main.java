import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	// генерация исходных данных
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
//            if(i%1000000==0){
//                System.out.println(i);
//            }
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //1. считаем несовершеннолетних
//        Comparator<Person> comparator1 = Comparator<Person>(Person.);
        long countYong = persons.stream()
                .filter(value -> (value.getAge() < 18))
                .count();
        System.out.println("Молодых (до 18) " + countYong);
        /*
        2. Для получения списка призывников потребуется применить несколько промежуточных методов filter(),
        а также для преобразования данных из Person в String (так как нужны только фамилии) используйте метод map().
        Так как требуется получить список List<String> терминальным методом будет collect(Collectors.toList())
        */
        List<String> listFamily = persons.stream()
                .filter(value->(value.getAge()>18))
                .filter(value->(value.getAge()<27))
                .map(value->(value.getFamily()))
                .collect(Collectors.toList());
        System.out.println("Всего в списке призывников" + listFamily.size());
        System.out.println("Например призывник под № 8 " + listFamily.get(8));
        /*
        3. Для получения отсортированного по фамилии списка потенциально работоспособных людей с высшим образованием
        необходимо применить ряд промежуточных методов filter(), метод sorted() в который нужно будет положить
        компаратор по фамилиям Comparator.comparing(). Завершить стрим необходимо методом collect()
         */
        Comparator<Person> comparator = Comparator.comparing(Person::getFamily);
        List<String> listWorkerMan = persons.stream()
                .filter(v->v.getEducation()==Education.HIGHER)
                .filter(v->v.getSex()==Sex.MAN)
                .filter(v->v.getAge()<65)
                .filter(v->v.getAge()>18)
                .map(value->(value.getFamily()))
                .collect(Collectors.toList());
        System.out.println("Список мужчин с высшим образованием готов, их " + listWorkerMan.size());
        List<String> listWorkerWoman = persons.stream()
                .filter(v->v.getEducation()==Education.HIGHER)
                .filter(v->v.getSex()==Sex.WOMAN)
                .filter(v->v.getAge()<60)
                .filter(v->v.getAge()>18)
                .map(value->(value.getFamily()))
                .collect(Collectors.toList());
        System.out.println("Список женщин с высшим образованием готов, их " + listWorkerWoman.size());
        List<String> listWorker = persons.stream()
                .filter(v->v.getAge()>18)
                .filter(v->v.getEducation()==Education.HIGHER)
                .filter(v->(v.getAge()<65&&v.getSex()==Sex.MAN)||(v.getAge()<60&&v.getSex()==Sex.WOMAN))
                .map(value->(value.getFamily()))
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        System.out.println("Список и муж и жен с высшим образованием готов их " + listWorker.size());
        for (int i = 400000; i < 400008; i++) {
            System.out.print(listWorker.get(i) + ", ");

        }










    }

}
