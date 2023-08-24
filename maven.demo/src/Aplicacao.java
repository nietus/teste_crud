import java.util.List;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		NovelDAO novelDAO = new NovelDAO();
		Menu.menu();
		String s = MyIO.readLine();
		while(s.toLowerCase().charAt(0) != 's') {
			
			if(s.toLowerCase().charAt(0) == 'i') {//inserir
				String chapter = "";
				System.out.println("Escreva o capitulo: ");
				chapter = MyIO.readLine(); 
				Novel novel = new Novel(chapter);
				if(novelDAO.insert(novel) == true) {
						System.out.println("Inserção com sucesso -> " + novel.toString());
				}
			}
			
			if(s.toLowerCase().charAt(0) == 'l') {//listar
				System.out.println("\n\n==== Capitulos === ");
				List<Novel> novels = novelDAO.getOrderById();
				for (Novel n: novels) {
					System.out.println(n.toString());
				}
			}
			
			if (s.toLowerCase().charAt(0) == 'a') { // atualizar
			    int chapterId = -1;
			    System.out.println("Qual capitulo quer atualizar: ");
			    chapterId = MyIO.readInt();
			    
			    System.out.println("Novo conteúdo do capitulo: ");
			    String newContent = MyIO.readLine();
			    
			    Novel updatedNovel = new Novel(newContent); // Create a new Novel object with updated content
			    updatedNovel.setId(chapterId); // Set the ID of the novel to be updated
			    
			    if (novelDAO.update(updatedNovel)) {
			        System.out.println("Atualização com sucesso -> " + updatedNovel.toString());
			    }
			}

				
			if(s.toLowerCase().charAt(0) == 'e') {//excluir
				int chapter = -1;
				System.out.println("Qual capitulo quer excluir: ");
				chapter = MyIO.readInt();
				novelDAO.delete(chapter);
			}
			Menu.menu();
			s = MyIO.readLine();
		}
		
	}
}