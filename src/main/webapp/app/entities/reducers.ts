import team from 'app/entities/team/team.reducer';
import landscape from 'app/entities/landscape/landscape.reducer';
import landscapeElement from 'app/entities/landscape-element/landscape-element.reducer';
import project from 'app/entities/project/project.reducer';
import projectElement from 'app/entities/project-element/project-element.reducer';
import textFileContent from 'app/entities/text-file-content/text-file-content.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  team,
  landscape,
  landscapeElement,
  project,
  projectElement,
  textFileContent,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
